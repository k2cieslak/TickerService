package io.github.k2cieslak.cryptoticker.tickerservice.configuration;

import io.github.k2cieslak.cryptoticker.tickerservice.ExchangeController;
import io.github.k2cieslak.cryptoticker.tickerservice.TickerController;
import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TickerController.class);
        register(TickerServiceException.class);
        register(ExchangeController.class);
    }

}