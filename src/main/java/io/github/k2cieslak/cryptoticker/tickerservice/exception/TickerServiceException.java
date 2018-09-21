package io.github.k2cieslak.cryptoticker.tickerservice.exception;

import org.knowm.xchange.dto.marketdata.Ticker;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TickerServiceException extends Exception implements ExceptionMapper<TickerServiceException> {

    public TickerServiceException() {
        super("General exception in Ticker Service");
    }

    public TickerServiceException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(TickerServiceException exception) {
        return Response.status(404).entity("{ \"Exception\":\"" + exception.getMessage() + "\" }")
                .type("application/json").build();
    }


}