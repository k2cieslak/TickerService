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
    private static final String COMMON_CURRENCY_PAIR = "BTC_USD";

    @Test
    @DisplayName("To show that rendom generic case works")
    void getTickerSmokeTest() {
        Ticker ticker = null;
        try {
            ticker = marketDataBean.getTicker("hitbtc", COMMON_CURRENCY_PAIR);
        } catch (TickerServiceException e) {
            e.printStackTrace();
        }
        assertNotNull(ticker);
    }

    @Test
    @DisplayName("Tests exception when exchangeName is wrong")
    void getTickerForWrongExchangeSymbolTest() {
        assertThrows(TickerServiceException.class,
                () -> marketDataBean.getTicker("xxx", COMMON_CURRENCY_PAIR));
    }

    @Test
    @DisplayName("Currency pair converter :: Happy case for ")
    void parseCurrencyPairSmokeTest() {
        CurrencyPair currencyPair = null;
        try {
            currencyPair = marketDataBean.parseCurrencyPair(COMMON_CURRENCY_PAIR);
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
                () -> marketDataBean.parseCurrencyPair(input));
    }
}
