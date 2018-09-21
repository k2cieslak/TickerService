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


    public Ticker getTicker(String exchangeName, String currencyPair) {

        String[] currencies = currencyPair.split("_");
        Currency base = new Currency(currencies[0]);
        Currency counter = new Currency(currencies[1]);
        CurrencyPair market = new CurrencyPair(base, counter);

        MarketDataService marketDataService = exchanges.get(exchangeName);

        Ticker ticker = null;
        try {
            ticker = marketDataService.getTicker(market);
        } catch (IOException e) {
            //TODO use logback
            e.printStackTrace();
        }

        return ticker;
    }

    CurrencyPair parseCurrencyPair(String input) throws TickerServiceException {
        if(input != null && input.length() > 3 && input.contains("_")) {
            String[] currencies = input.split("_");
            if(currencies.length == 2) {
                Currency base = new Currency(currencies[0]);
                Currency counter = new Currency(currencies[1]);
                CurrencyPair market = new CurrencyPair(base, counter);
                return market;
            } else throw new TickerServiceException();
        } else throw new TickerServiceException();
    }
}
