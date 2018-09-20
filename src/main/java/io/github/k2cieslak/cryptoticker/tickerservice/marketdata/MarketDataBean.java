package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

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
}
