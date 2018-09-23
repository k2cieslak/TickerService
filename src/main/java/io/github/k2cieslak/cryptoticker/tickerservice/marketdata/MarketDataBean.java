package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarketDataBean {

    private static final Logger logger = LoggerFactory.getLogger(MarketDataBean.class);
    private final Map<String, MarketDataService> exchanges;
    private Map<String, Map<CurrencyPair, Ticker>> cachedTickers;

    public MarketDataBean() {
        long startTime = System.currentTimeMillis();

        exchanges = MarketDataRegistry.buildMarketDataSources();

        String startMessage = String.format("================ INIT OF %d EXCHANGES IN %d" , exchanges.size(), System.currentTimeMillis()-startTime);
        //TODO exception levels
        logger.info(startMessage);
    }


    public Ticker getTicker(String exchangeName, String currencyPair) throws TickerServiceException {

        CurrencyPair market = parseCurrencyPair(currencyPair);
        MarketDataService marketDataService = exchanges.get(exchangeName);

        if(marketDataService == null) {
            throw new TickerServiceException("Exchange name - misspeled or not supported exchange.");
        }

        Ticker ticker;
        try {
            ticker = marketDataService.getTicker(market);
        } catch (IOException e) {
            throw new TickerServiceException("Problem while communicating with exchange.");
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
}
