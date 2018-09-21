package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MarketDataBean {

    private Map<String, MarketDataService> exchanges = new HashMap<>();

    public MarketDataBean() {
        long startTime = System.currentTimeMillis();

        exchanges = MarketDataRegistry.buildMarketDataSources();

        //TODO use logback / update actuator
        System.out.println("================ INIT IN " + (System.currentTimeMillis()-startTime));
    }


    public Ticker getTicker(String exchangeName, String currencyPair) throws TickerServiceException {

        CurrencyPair market = parseCurrencyPair(currencyPair);
        MarketDataService marketDataService = exchanges.get(exchangeName);

        if(marketDataService == null) {
            throw new TickerServiceException("Exchange name - misspeled or not supported exchange.");
        }

        Ticker ticker = null;
        try {
            ticker = marketDataService.getTicker(market);
        } catch (IOException e) {
            throw new TickerServiceException("Problem while communicating with exchange.");
        }

        return ticker;
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
