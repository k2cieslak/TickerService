package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.anx.v2.ANXExchange;
import org.knowm.xchange.bibox.BiboxExchange;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.bitbay.BitbayExchange;
import org.knowm.xchange.bitcoinaverage.BitcoinAverageExchange;
import org.knowm.xchange.bitcoincharts.BitcoinChartsExchange;
import org.knowm.xchange.bitcoinde.BitcoindeExchange;
import org.knowm.xchange.bitcoinium.BitcoiniumExchange;
import org.knowm.xchange.bitfinex.v2.BitfinexExchange;
import org.knowm.xchange.bitflyer.BitflyerExchange;
import org.knowm.xchange.bitmex.BitmexExchange;
import org.knowm.xchange.bitso.BitsoExchange;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.bittrex.BittrexExchange;
import org.knowm.xchange.bity.BityExchange;
import org.knowm.xchange.bleutrade.BleutradeExchange;
import org.knowm.xchange.btcc.BTCCExchange;
import org.knowm.xchange.btcmarkets.BTCMarketsExchange;
import org.knowm.xchange.btctrade.BTCTradeExchange;
import org.knowm.xchange.btcturk.BTCTurkExchange;
import org.knowm.xchange.bx.BxExchange;
import org.knowm.xchange.campbx.CampBXExchange;
import org.knowm.xchange.ccex.CCEXExchange;
import org.knowm.xchange.cexio.CexIOExchange;
import org.knowm.xchange.cobinhood.CobinhoodExchange;
import org.knowm.xchange.gemini.v1.GeminiExchange;
import org.knowm.xchange.hitbtc.v2.HitbtcExchange;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.poloniex.PoloniexExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.known.xchange.acx.AcxExchange;
import org.xchange.bitz.BitZExchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class MarketDataRegistry {

    private static final Map<String, Class> exchangeSpec = new HashMap<String, Class>();
    static {
        exchangeSpec.put("ack", AcxExchange.class);

        exchangeSpec.put("anx", ANXExchange.class);
        exchangeSpec.put("bibox", BiboxExchange.class);
        exchangeSpec.put("binance", BinanceExchange.class);
        exchangeSpec.put("bitbay", BitbayExchange.class);
        exchangeSpec.put("bitcoinaverage", BitcoinAverageExchange.class);
        exchangeSpec.put("bitcoincharts", BitcoinChartsExchange.class);
        exchangeSpec.put("bitcoinde", BitcoindeExchange.class);
        exchangeSpec.put("bitcoinium", BitcoiniumExchange.class);
        exchangeSpec.put("bitfinex", BitfinexExchange.class);
        exchangeSpec.put("bitflyer", BitflyerExchange.class);
        exchangeSpec.put("bitmex", BitmexExchange.class);
        exchangeSpec.put("bitso", BitsoExchange.class);
        exchangeSpec.put("bitstamp", BitstampExchange.class);
        exchangeSpec.put("bittrex", BittrexExchange.class);
        exchangeSpec.put("bity", BityExchange.class);
        exchangeSpec.put("bitz", BitZExchange.class);
        exchangeSpec.put("bleutrade", BleutradeExchange.class);
        exchangeSpec.put("btcturk", BTCTurkExchange.class);
        exchangeSpec.put("btcc", BTCCExchange.class);
        exchangeSpec.put("btcmarkets", BTCMarketsExchange.class);
        exchangeSpec.put("btctrade", BTCTradeExchange.class);
        exchangeSpec.put("bx", BxExchange.class);
        exchangeSpec.put("ccex", CCEXExchange.class);
        exchangeSpec.put("campbx", CampBXExchange.class);
        exchangeSpec.put("cexio", CexIOExchange.class);
        exchangeSpec.put("cobinhood", CobinhoodExchange.class);

        exchangeSpec.put("gemini", GeminiExchange.class);
        exchangeSpec.put("hitbtc", HitbtcExchange.class);
        exchangeSpec.put("kraken", KrakenExchange.class);
        exchangeSpec.put("poloniex", PoloniexExchange.class);
    }

    private MarketDataRegistry() {

    }

    //11 na 82
    public static Map<String, MarketDataService> buildMarketDataSources() {
        List<CompletableFuture<ExchangeListEntry>> exchangeThreadPool = new ArrayList<>();
        Map<String, MarketDataService> exchanges = new HashMap<>();

        for(String exchangeName : exchangeSpec.keySet()) {
            MarketDataRegistryWorker marketDataRegistryWorker = new MarketDataRegistryWorker(exchangeName, exchangeSpec.get(exchangeName));
            exchangeThreadPool.add(CompletableFuture.supplyAsync(marketDataRegistryWorker));
        }

        CompletableFuture.allOf((CompletableFuture<?>[]) exchangeThreadPool.toArray(new CompletableFuture[exchangeThreadPool.size()])).join();

        for(CompletableFuture<ExchangeListEntry> exchangeThread : exchangeThreadPool) {
            ExchangeListEntry exchangeListEntry = null;

            try {
                exchangeListEntry = exchangeThread.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //TODO keep list of greyed exchanges and try to recover
            if(exchangeListEntry != null && exchangeListEntry.getMarketDataService() != null) {
                exchanges.put(exchangeListEntry.getExchangeName(), exchangeListEntry.getMarketDataService());
            }
        }


        //        Exchange acx = ExchangeFactory.INSTANCE.createExchange(AcxExchange.class.getName());
//        MarketDataService acxMDS = acx.getMarketDataService();
//        ExchangeSpecification spec = acx.getExchangeSpecification();
//        acx.applySpecification(spec);
//        exchanges.put("acx", acxMDS);
//
//        Exchange anx = ExchangeFactory.INSTANCE.createExchange(ANXExchange.class.getName());
//        MarketDataService anxMDS = anx.getMarketDataService();
//        exchanges.put("anx", anxMDS);
//
//        Exchange bibox = ExchangeFactory.INSTANCE.createExchange(BiboxExchange.class.getName());
//        MarketDataService biboxMDS = bibox.getMarketDataService();
//        exchanges.put("bibox", biboxMDS);
//
//        Exchange bitbay = ExchangeFactory.INSTANCE.createExchange(BitbayExchange.class.getName());
//        MarketDataService bitbayMDS = bitbay.getMarketDataService();
//        exchanges.put("bitbay", bitbayMDS);
//
//        Exchange bitfinex = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class.getName());
//        MarketDataService bitfinexMDS = bitfinex.getMarketDataService();
//        exchanges.put("bitfinex", bitfinexMDS);
//
//        Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
//        MarketDataService bitstampMDS = bitstamp.getMarketDataService();
//        exchanges.put("bitstamp", bitstampMDS);
//
//        Exchange bittrex = ExchangeFactory.INSTANCE.createExchange(BittrexExchange.class.getName());
//        MarketDataService bittrexMDS = bittrex.getMarketDataService();
//        exchanges.put("bittrex", bittrexMDS);
//
//        Exchange gemini = ExchangeFactory.INSTANCE.createExchange(GeminiExchange.class.getName());
//        MarketDataService geminiMDS = gemini.getMarketDataService();
//        exchanges.put("gemini", geminiMDS);
//
//        Exchange hitbtc = ExchangeFactory.INSTANCE.createExchange(HitbtcExchange.class.getName());
//        MarketDataService hitbtcMDS = hitbtc.getMarketDataService();
//        exchanges.put("hitbtc", hitbtcMDS);
//
//        Exchange kraken = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
//        MarketDataService krakenMDS = kraken.getMarketDataService();
//        exchanges.put("kraken", krakenMDS);
//
//        Exchange poloniex = ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
//        MarketDataService poloniexMDS = poloniex.getMarketDataService();
//        exchanges.put("poloniex", poloniexMDS);

        return exchanges;
    }
}
