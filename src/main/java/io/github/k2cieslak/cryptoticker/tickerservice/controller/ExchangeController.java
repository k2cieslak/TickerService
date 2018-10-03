package io.github.k2cieslak.cryptoticker.tickerservice.controller;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

@Component
@Path("/exchange")
@Api(value = "Exchange data", produces = "application/json")
public class ExchangeController {

    @Autowired
    private MarketDataBean marketData;

    @GET
    @Produces("application/json")
    @Path("/avaliable")
    @ApiOperation(value = "Gets list of available exchanges.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of exchange codes."),
            @ApiResponse(code = 404, message = "Exception during method call.")
    })
    public List<String> getAvaliableExchanges() {

        return marketData.getAvaliableExchanges();
    }

    @GET
    @Produces("application/json")
    @Path("/gray")
    @ApiOperation(value = "Gets list of exchanges that service was not able to initialize.",
                    response = List.class,
                    responseContainer = "List" )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of exchange codes."),
            @ApiResponse(code = 404, message = "Exception during method call.")
    })
    public List<String> getGrayExchanges() {

        return marketData.getGrayExchanges();
    }

    @GET
    @Produces("application/json")
    @Path("/markets")
    @ApiOperation(value = "Gets List of available markets for a given exchange.",
                    response = List.class,
                    responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of market codes."),
            @ApiResponse(code = 404, message = "Exception during method call.")
    })
    public List<String> getExchangeMarkets(@QueryParam("exchangeName") String exchangeName) throws TickerServiceException {

        return marketData.getExchangeMarkets(exchangeName);
    }
}
