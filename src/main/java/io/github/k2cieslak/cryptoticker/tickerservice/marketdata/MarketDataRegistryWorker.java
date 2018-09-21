package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.exceptions.ExchangeException;

import java.util.function.Supplier;

public class MarketDataRegistryWorker implements Supplier<ExchangeListEntry> {

    private String exchangeName;
    private Class exchangeProvider;

    public MarketDataRegistryWorker(String exchangeName, Class exchangeProvider) {
        this.exchangeName = exchangeName;
        this.exchangeProvider = exchangeProvider;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    @Override
    public ExchangeListEntry get() {
        ExchangeListEntry exchangeListEntry = new ExchangeListEntry();
        exchangeListEntry.setExchangeName(exchangeName);
        Exchange exchange = null;
        try {
            exchange = ExchangeFactory.INSTANCE.createExchange(exchangeProvider.getName());
        } catch (ExchangeException exception) {
            exception.printStackTrace();
        }
        if(exchange != null) {
            exchangeListEntry.setMarketDataService(exchange.getMarketDataService());
        }
        return exchangeListEntry;
    }
}
