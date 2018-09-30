package io.github.k2cieslak.cryptoticker.tickerservice.controller;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Component
@Path("/ticker")
public class TickerController {

    @Autowired
    private MarketDataBean marketData;

    @GET
    @Produces("application/json")
    public Ticker getTicker(@QueryParam("exchangeName") String exchangeName, @QueryParam("currencyPair")String currencyPair) throws TickerServiceException {
        return marketData.getTicker(exchangeName, currencyPair);
    }

}