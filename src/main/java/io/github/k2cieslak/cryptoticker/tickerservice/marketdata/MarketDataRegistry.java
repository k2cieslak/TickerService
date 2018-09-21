package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.anx.v2.ANXExchange;
import org.knowm.xchange.bibox.BiboxExchange;
import org.knowm.xchange.bitbay.BitbayExchange;
import org.knowm.xchange.bitfinex.v2.BitfinexExchange;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.bittrex.BittrexExchange;
import org.knowm.xchange.gdax.GDAXExchange;
import org.knowm.xchange.gemini.v1.GeminiExchange;
import org.knowm.xchange.hitbtc.v2.HitbtcExchange;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.poloniex.PoloniexExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.known.xchange.acx.AcxExchange;

import java.util.HashMap;
import java.util.Map;

public class MarketDataRegistry {

    private MarketDataRegistry() {

    }

    //12 na 83
    public static Map<String, MarketDataService> buildMarketDataSources() {
        Map<String, MarketDataService> exchanges = new HashMap<>();

        Exchange acx = ExchangeFactory.INSTANCE.createExchange(AcxExchange.class.getName());
        MarketDataService acxMDS = acx.getMarketDataService();
        ExchangeSpecification spec = acx.getExchangeSpecification();
        acx.applySpecification(spec);
        exchanges.put("acx", acxMDS);

        Exchange anx = ExchangeFactory.INSTANCE.createExchange(ANXExchange.class.getName());
        MarketDataService anxMDS = anx.getMarketDataService();
        exchanges.put("anx", anxMDS);

        Exchange bibox = ExchangeFactory.INSTANCE.createExchange(BiboxExchange.class.getName());
        MarketDataService biboxMDS = bibox.getMarketDataService();
        exchanges.put("bibox", biboxMDS);

        Exchange bitbay = ExchangeFactory.INSTANCE.createExchange(BitbayExchange.class.getName());
        MarketDataService bitbayMDS = bitbay.getMarketDataService();
        exchanges.put("bitbay", bitbayMDS);

        Exchange bitfinex = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class.getName());
        MarketDataService bitfinexMDS = bitfinex.getMarketDataService();
        exchanges.put("bitfinex", bitfinexMDS);

        Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
        MarketDataService bitstampMDS = bitstamp.getMarketDataService();
        exchanges.put("bitstamp", bitstampMDS);

        Exchange bittrex = ExchangeFactory.INSTANCE.createExchange(BittrexExchange.class.getName());
        MarketDataService bittrexMDS = bittrex.getMarketDataService();
        exchanges.put("bittrex", bittrexMDS);

        Exchange gdax = ExchangeFactory.INSTANCE.createExchange(GDAXExchange.class.getName());
        MarketDataService gdaxMDS = gdax.getMarketDataService();
        exchanges.put("gdax", gdaxMDS);

        Exchange gemini = ExchangeFactory.INSTANCE.createExchange(GeminiExchange.class.getName());
        MarketDataService geminiMDS = gemini.getMarketDataService();
        exchanges.put("gemini", geminiMDS);

        Exchange hitbtc = ExchangeFactory.INSTANCE.createExchange(HitbtcExchange.class.getName());
        MarketDataService hitbtcMDS = hitbtc.getMarketDataService();
        exchanges.put("hitbtc", hitbtcMDS);

        Exchange kraken = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
        MarketDataService krakenMDS = kraken.getMarketDataService();
        exchanges.put("kraken", krakenMDS);

        //TODO strange response
        Exchange poloniex = ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
        MarketDataService poloniexMDS = poloniex.getMarketDataService();
        exchanges.put("poloniex", poloniexMDS);

        return exchanges;
    }
}
