package io.github.k2cieslak.cryptoticker.tickerservice.configuration;

import io.github.k2cieslak.cryptoticker.tickerservice.controller.ExchangeController;
import io.github.k2cieslak.cryptoticker.tickerservice.controller.TickerController;
import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
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
        this.configureSwagger();
    }

    private void configureSwagger() {
        // Available at localhost:port/api/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("CryptoTickerService");
        config.setTitle("Crypto Ticker Service REST API");
        config.setVersion("v1");
        config.setContact("Krzysztof Cieslak");
        config.setSchemes(new String[]{"http"});
        config.setBasePath("/api");
        config.setResourcePackage("io.github.k2cieslak.cryptoticker.tickerservice.controller");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}