package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import static org.junit.jupiter.api.Assertions.*;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

public class MarketDataBeanTest {

    static MarketDataBean marketDataBean =  new MarketDataBean();

    @Test
    @DisplayName("To show that rendom generic case works")
    void getTickerSmokeTest() {
        Ticker ticker = marketDataBean.getTicker("hitbtc", "BTC_USD");
        assertNotNull(ticker);
    }

    @Test
    @DisplayName("Currency pair converter :: Happy case for ")
    void parseCurrencyPairSmokeTest() {
        CurrencyPair currencyPair = null;
        try {
            currencyPair = marketDataBean.parseCurrencyPair("BTC_USD");
        } catch (TickerServiceException e) {
            e.printStackTrace();
        }
        assertEquals(CurrencyPair.BTC_USD, currencyPair);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "BTCUSD", "BTC_USD_RPG", "_", "______" })
    @DisplayName("Currency pair converter :: malformed inputs")
    void parseCurrencyPairMalformedInputTest(String input) {
        assertThrows(TickerServiceException.class,
                () -> {
                    marketDataBean.parseCurrencyPair(input);
                });
    }
}
