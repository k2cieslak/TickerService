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
    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hello resource found"),
            @ApiResponse(code = 404, message = "Given admin user not found")
    })
    public List<String> getAvaliableExchanges() {

        return marketData.getAvaliableExchanges();
    }

    @GET
    @Produces("application/json")
    @Path("/gray")
    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hello resource found"),
            @ApiResponse(code = 404, message = "Given admin user not found")
    })
    public List<String> getGrayExchanges() {

        return marketData.getGrayExchanges();
    }

    @GET
    @Produces("application/json")
    @Path("/markets")
    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "hello resource found"),
            @ApiResponse(code = 404, message = "Given admin user not found")
    })
    public List<String> getExchangeMarkets(@QueryParam("exchangeName") String exchangeName) throws TickerServiceException {

        return marketData.getExchangeMarkets(exchangeName);
    }
}
