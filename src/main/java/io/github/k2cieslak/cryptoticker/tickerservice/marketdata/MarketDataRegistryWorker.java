package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.exceptions.ExchangeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class MarketDataRegistryWorker implements Supplier<ExchangeListEntry> {

    private static final Logger logger = LoggerFactory.getLogger(MarketDataRegistryWorker.class);
    private String exchangeName;
    private Class exchangeProvider;

    MarketDataRegistryWorker(String exchangeName, Class exchangeProvider) {
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

        try {
            Exchange exchange = ExchangeFactory.INSTANCE.createExchange(exchangeProvider.getName());
            exchangeListEntry.setExchange(exchange);
        } catch (ExchangeException exception) {
            logger.warn(exception.getMessage(), exception);
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
        }

        return exchangeListEntry;
    }
}
