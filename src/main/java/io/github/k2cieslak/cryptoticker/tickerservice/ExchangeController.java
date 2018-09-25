package io.github.k2cieslak.cryptoticker.tickerservice;

import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

@Component
@Path("/exchange")
public class ExchangeController {

    @Autowired
    private MarketDataBean marketData;

    @GET
    @Produces("application/json")
    @Path("/avaliable")
    public List<String> getAvaliableExchanges() {
        return marketData.getAvaliableExchanges();
    }

    @GET
    @Produces("application/json")
    @Path("/gray")
    public List<String> getGrayExchanges() {
        return marketData.getGrayExchanges();
    }

    @GET
    @Produces("application/json")
    @Path("/markets")
    public List<String> getExchangeMarkets(@QueryParam("exchangeName") String exchangeName) {
        return marketData.getExchangeMarkets(exchangeName);
    }
}
