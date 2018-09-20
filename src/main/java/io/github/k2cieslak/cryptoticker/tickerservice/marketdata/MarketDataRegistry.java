package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.anx.v2.ANXExchange;
import org.knowm.xchange.bibox.BiboxExchange;
import org.knowm.xchange.bitbay.BitbayExchange;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.known.xchange.acx.AcxExchange;

import java.util.HashMap;
import java.util.Map;

public class MarketDataRegistry {

    public static Map<String, MarketDataService> buildMarketDataSources() {
        Map<String, MarketDataService> exchanges = new HashMap<>();

        Exchange acx = ExchangeFactory.INSTANCE.createExchange(AcxExchange.class.getName());
        MarketDataService acxMDS = acx.getMarketDataService();
        ExchangeSpecification spec = acx.getExchangeSpecification();
        acx.applySpecification(spec);
        exchanges.put("acx", acxMDS);

        //TODO cos nie dziala
        Exchange anx = ExchangeFactory.INSTANCE.createExchange(ANXExchange.class.getName());
        MarketDataService anxMDS = acx.getMarketDataService();
        exchanges.put("anx", anxMDS);

        Exchange bibox = ExchangeFactory.INSTANCE.createExchange(BiboxExchange.class.getName());
        MarketDataService biboxMDS = acx.getMarketDataService();
        exchanges.put("bibox", biboxMDS);

        Exchange bitbay = ExchangeFactory.INSTANCE.createExchange(BitbayExchange.class.getName());
        MarketDataService bitbayMDS = bitbay.getMarketDataService();
        exchanges.put("bitbay", bitbayMDS);

        Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
        MarketDataService bitstampMDS = bitstamp.getMarketDataService();
        exchanges.put("bitstamp", bitstampMDS);

        return exchanges;
    }
}
