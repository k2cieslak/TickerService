package io.github.k2cieslak.cryptoticker.tickerservice.configuration;

import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public MarketDataBean marketData() {
        return new MarketDataBean();
    }


}
