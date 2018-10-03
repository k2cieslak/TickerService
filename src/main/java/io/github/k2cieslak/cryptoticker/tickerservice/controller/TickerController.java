package io.github.k2cieslak.cryptoticker.tickerservice.controller;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import io.swagger.annotations.Api;
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


@Component
@Path("/ticker")
@Api(value = "Ticker data", produces = "application/json")
public class TickerController {

    @Autowired
    private MarketDataBean marketData;

    @GET
    @Produces("application/json")
    @ApiOperation(value = "Get current ticker for given exchange code and market code.)", response = Ticker.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Current ticker codes."),
            @ApiResponse(code = 404, message = "Exception during method call.")
    })
    public Ticker getTicker(@QueryParam("exchangeName") String exchangeName, @QueryParam("currencyPair")String currencyPair) throws TickerServiceException {
        return marketData.getTicker(exchangeName, currencyPair);
    }

}
