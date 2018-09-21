package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.knowm.xchange.dto.marketdata.Ticker;

public class MarketDataBeanTest {
    @Test
    @DisplayName("To show that anything works")
    void getTickerSmokeTest() {
        MarketDataBean marketDataBean =  new MarketDataBean();
        Ticker ticker = marketDataBean.getTicker("hitbtc", "BTC_USD");
        assertNotNull(ticker);
    }
}
