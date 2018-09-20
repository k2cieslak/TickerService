package io.github.k2cieslak.cryptoticker.tickerservice;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.IOException;


@Component
@Path("/ticker")
public class TickerController {

    @GET
    @Produces("application/json")
    public Ticker getTicker() {

        Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());

        MarketDataService marketDataService = bitstamp.getMarketDataService();

        Ticker ticker = null;
        try {
            ticker = marketDataService.getTicker(CurrencyPair.BTC_USD);
        } catch (IOException e) {
            //TODO use logback
            e.printStackTrace();
        }

        return ticker;
    }
}
