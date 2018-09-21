package io.github.k2cieslak.cryptoticker.tickerservice.configuration;

import io.github.k2cieslak.cryptoticker.tickerservice.marketdata.MarketDataBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfiguration {

    @Bean
    public MarketDataBean marketData() {
        return new MarketDataBean();
    }


}
