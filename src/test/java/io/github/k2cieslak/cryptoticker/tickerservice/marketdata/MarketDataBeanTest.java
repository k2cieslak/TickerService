package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import static org.junit.jupiter.api.Assertions.*;

import io.github.k2cieslak.cryptoticker.tickerservice.exception.TickerServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketDataBeanTest {

    private static final MarketDataBean marketDataBean =  new MarketDataBean();
    private static final String COMMON_CURRENCY_PAIR = "BTC/USD";
    private static final String EXAMPLE_EXCHANGE = "hitbtc";
    private static final Logger logger = LoggerFactory.getLogger(MarketDataBean.class);

    @Test
    @DisplayName("To show that random generic case works")
    void getTickerSmokeTest() {
        Ticker ticker = null;
        try {
            ticker = marketDataBean.getTicker(EXAMPLE_EXCHANGE, COMMON_CURRENCY_PAIR);
        } catch (TickerServiceException e) {
            logger.warn(e.toString());
        }
        assertNotNull(ticker);
    }

    @Test
    @DisplayName("Tests exception when market is not supported on exchange")
    void getTickerForWrongMarketSymbolTest() {
        assertThrows(TickerServiceException.class,
                () -> marketDataBean.getTicker(EXAMPLE_EXCHANGE, "BTC/EUR"));
    }

    @Test
    @DisplayName("Tests exception when exchangeName is wrong")
    void getTickerForWrongExchangeSymbolTest() {
        assertThrows(TickerServiceException.class,
                () -> marketDataBean.getTicker("xxx", COMMON_CURRENCY_PAIR));
    }

    @Test
    @DisplayName("Testing ticker caching feature")
    void getTickerFromCacheTest() {
        Ticker ticker1 = null;
        Ticker ticker2 = null;

        try {
            ticker1 = marketDataBean.getTicker(EXAMPLE_EXCHANGE, COMMON_CURRENCY_PAIR);
        } catch (TickerServiceException e) {
            logger.warn(e.toString());
        }

        try {
            ticker2 = marketDataBean.getTicker(EXAMPLE_EXCHANGE, COMMON_CURRENCY_PAIR);
        } catch (TickerServiceException e) {
            logger.warn(e.toString());
        }

        assertEquals(ticker1, ticker2);
    }

    @Test
    @DisplayName("Testing ticker cache expiration feature")
    void getTickerCacheExpirationTest() {
        Ticker ticker1 = null;
        Ticker ticker2 = null;

        try {
            ticker1 = marketDataBean.getTicker(EXAMPLE_EXCHANGE, COMMON_CURRENCY_PAIR);
        } catch (TickerServiceException e) {
            logger.warn(e.toString());
        }

        try {
            Thread.sleep(MarketDataBean.CACHE_VALIDITY_INTERVAL+1);
        } catch (InterruptedException e) {
            logger.warn(e.toString());
        }


        try {
            ticker2 = marketDataBean.getTicker(EXAMPLE_EXCHANGE, COMMON_CURRENCY_PAIR);
        } catch (TickerServiceException e) {
            logger.warn(e.toString());
        }

        assertNotEquals(ticker1, ticker2);
    }

    @Test
    @DisplayName("Get avaliable exchanges smoke test")
    void getAvaliableExchangesSmokeTest() {
        assertNotNull(marketDataBean.getAvaliableExchanges());
    }

    @Test
    @DisplayName("Get gray exchanges smoke test")
    void getGrayExchangesSmokeTest() {
        assertNotNull(marketDataBean.getGrayExchanges());
    }

    @Test
    @DisplayName("Get exchange markets smoke test")
    void getExchangeMarketsSmokeTest() {
        try {
            assertNotNull(marketDataBean.getExchangeMarkets(EXAMPLE_EXCHANGE));
        } catch (TickerServiceException e) {
            logger.warn(e.toString());
        }
    }

    @Test
    @DisplayName("Get exchange markets for wrong exchange name")
    void getExchangeMarketsForWrongExchangeNameTest() {
        assertThrows(TickerServiceException.class,
                () -> marketDataBean.getExchangeMarkets("xxx"));
    }
}
