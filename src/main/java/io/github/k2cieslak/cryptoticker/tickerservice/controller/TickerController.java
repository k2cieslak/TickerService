package io.github.k2cieslak.cryptoticker.tickerservice.controller;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;


@Component
@Path("/ticker")
public class TickerController {

    @Autowired
    private MarketDataBean marketData;

    @GET
    @Produces("application/json")
    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = Ticker.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hello resource found"),
            @ApiResponse(code = 404, message = "Given admin user not found")
    })
    public Ticker getTicker(@QueryParam("exchangeName") String exchangeName, @QueryParam("currencyPair")String currencyPair) throws TickerServiceException {
        return marketData.getTicker(exchangeName, currencyPair);
    }

}
