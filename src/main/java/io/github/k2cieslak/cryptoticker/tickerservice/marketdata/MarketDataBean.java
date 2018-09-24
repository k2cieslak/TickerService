package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.exceptions.CurrencyPairNotValidException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketDataBean {

    private static final Logger logger = LoggerFactory.getLogger(MarketDataBean.class);
    static final long CACHE_VALIDITY_INTERVAL = 10000L;
    private final Map<String, MarketDataService> exchanges;
    private Map<String, Map<String, TimestampedTicker>> cachedTickers = new HashMap<>();

    public MarketDataBean() {
        long startTime = System.currentTimeMillis();
        exchanges = MarketDataRegistry.buildMarketDataSources();
        String startMessage = String.format("================ INIT OF %d EXCHANGES IN %d" , exchanges.size(), System.currentTimeMillis()-startTime);
        logger.info(startMessage);
    }


    public Ticker getTicker(String exchangeName, String currencyPair) throws TickerServiceException {

        MarketDataService marketDataService = exchanges.get(exchangeName);

        if(marketDataService == null) {
            throw new TickerServiceException("Exchange name - misspeled or not supported exchange.");
        }

        Ticker ticker;
        TimestampedTicker tt = getCachedTicker(exchangeName, currencyPair);
        Ticker tickcer;
        if(tt == null) {
            try {
                CurrencyPair market = parseCurrencyPair(currencyPair);
                ticker = marketDataService.getTicker(market);
            } catch (IOException e) {
                throw new TickerServiceException("Problem while communicating with exchange.");
            } catch (CurrencyPairNotValidException currencyPairNotValidException) {
                throw new TickerServiceException("Currency pair not valid for exchange.");
            }

            Map<String, TimestampedTicker > exchange = cachedTickers.get(exchangeName);
            if(exchange != null) {
                tt = new TimestampedTicker(System.currentTimeMillis(), ticker);
                if(!exchange.containsKey(currencyPair)) {
                    exchange.put(currencyPair, tt);
                } else {
                    exchange.replace(currencyPair, tt);
                }
            } else {
                Map<String, TimestampedTicker> exchangeEntries = new HashMap<>();
                tt = new TimestampedTicker(System.currentTimeMillis(), ticker);
                exchangeEntries.put(currencyPair, tt);
                cachedTickers.put(exchangeName, exchangeEntries);
            }
        } else {
            ticker = tt.getTicker();
        }


        return ticker;
    }

    public List<String> getAvaliableExchanges() {
        List<String> result = new ArrayList<>();
        result.addAll(exchanges.keySet());
        return result;
    }

    public List<String> getGrayExchanges() {
        List<String> result = new ArrayList<>();

        for(String exchangeName : MarketDataRegistry.getAllExchangeNames()) {
            if(!exchanges.containsKey(exchangeName)) {
                result.add(exchangeName);
            }
        }

        return result;
    }

    CurrencyPair parseCurrencyPair(String input) throws TickerServiceException {
        if(input != null && input.length() > 3 && input.contains("_")) {
            String[] currencies = input.split("_");
            if(currencies.length == 2) {
                Currency base = new Currency(currencies[0]);
                Currency counter = new Currency(currencies[1]);
                return new CurrencyPair(base, counter);
            } else throw new TickerServiceException("Currency pair - wrong format.");
        } else throw new TickerServiceException("Currency pair - wrong format.");
    }

    private TimestampedTicker getCachedTicker(String exchangeName, String currencyPair) {
        Map<String, TimestampedTicker> exchange = cachedTickers.get(exchangeName);
        if(exchange != null) {
            TimestampedTicker tt = exchange.get(currencyPair);
            if(tt != null && (System.currentTimeMillis() - tt.getTimestamp()) < CACHE_VALIDITY_INTERVAL) {
                return tt;
            }
        }
        return null;
    }
}
