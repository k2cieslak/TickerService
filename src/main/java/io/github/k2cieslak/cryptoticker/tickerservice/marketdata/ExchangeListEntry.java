package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.service.marketdata.MarketDataService;

public class ExchangeListEntry {

    private String exchangeName;
    private MarketDataService marketDataService;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public MarketDataService getMarketDataService() {
        return marketDataService;
    }

    public void setMarketDataService(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }
}


