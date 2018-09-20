package io.github.k2cieslak.cryptoticker.tickerservice;

import io.github.k2cieslak.cryptoticker.tickerservice.pojo.Ticker;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



import java.math.BigDecimal;

@Component
@Path("/ticker")
public class TickerController {

    @GET
    @Produces("application/json")
    public Ticker getTicker() {
        return new Ticker(System.currentTimeMillis(),
                BigDecimal.valueOf(666),
                BigDecimal.valueOf(666),
                BigDecimal.valueOf(666));
    }
}
