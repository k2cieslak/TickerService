package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.acx.AcxExchange;
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
import org.knowm.xchange.bitz.BitZExchange;
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
import org.knowm.xchange.coinbase.CoinbaseExchange;
import org.knowm.xchange.coinbasepro.CoinbaseProExchange;
import org.knowm.xchange.coinbene.CoinbeneExchange;
import org.knowm.xchange.coindirect.CoindirectExchange;
import org.knowm.xchange.coinegg.CoinEggExchange;
import org.knowm.xchange.coinfloor.CoinfloorExchange;
import org.knowm.xchange.coingi.CoingiExchange;
import org.knowm.xchange.coinmarketcap.CoinMarketCapExchange;
import org.knowm.xchange.coinmate.CoinmateExchange;
import org.knowm.xchange.coinone.CoinoneExchange;
import org.knowm.xchange.coinsuper.CoinsuperExchange;
import org.knowm.xchange.cryptonit2.CryptonitExchange;
import org.knowm.xchange.cryptopia.CryptopiaExchange;
import org.knowm.xchange.dsx.DSXExchange;
import org.knowm.xchange.exmo.ExmoExchange;
import org.knowm.xchange.exx.EXXExchange;
import org.knowm.xchange.fcoin.FCoinExchange;
import org.knowm.xchange.gatecoin.GatecoinExchange;
import org.knowm.xchange.gateio.GateioExchange;
import org.knowm.xchange.gemini.v1.GeminiExchange;
import org.knowm.xchange.hitbtc.v2.HitbtcExchange;
import org.knowm.xchange.huobi.HuobiExchange;
import org.knowm.xchange.idex.IdexExchange;
import org.knowm.xchange.independentreserve.IndependentReserveExchange;
import org.knowm.xchange.itbit.v1.ItBitExchange;
import org.knowm.xchange.koineks.KoineksExchange;
import org.knowm.xchange.koinim.KoinimExchange;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.knowm.xchange.kuna.KunaExchange;
import org.knowm.xchange.lakebtc.LakeBTCExchange;
import org.knowm.xchange.liqui.LiquiExchange;
import org.knowm.xchange.livecoin.LivecoinExchange;
import org.knowm.xchange.luno.LunoExchange;
import org.knowm.xchange.mercadobitcoin.MercadoBitcoinExchange;
import org.knowm.xchange.oer.OERExchange;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.knowm.xchange.paribu.ParibuExchange;
import org.knowm.xchange.paymium.PaymiumExchange;
import org.knowm.xchange.poloniex.PoloniexExchange;
import org.knowm.xchange.quadrigacx.QuadrigaCxExchange;
import org.knowm.xchange.quoine.QuoineExchange;
import org.knowm.xchange.ripple.RippleExchange;
import org.knowm.xchange.therock.TheRockExchange;
import org.knowm.xchange.upbit.UpbitExchange;
import org.knowm.xchange.vaultoro.VaultoroExchange;
import org.knowm.xchange.vircurex.VircurexExchange;
import org.knowm.xchange.wex.v3.WexExchange;
import org.knowm.xchange.yobit.YoBitExchange;
import org.knowm.xchange.zaif.ZaifExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class MarketDataRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketDataRegistry.class);
    private static final Map<String, Class> exchangeSpec = new HashMap<>();

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
        exchangeSpec.put("cobinbase", CoinbaseExchange.class);
        exchangeSpec.put("cobinbasepro", CoinbaseProExchange.class);
        exchangeSpec.put("cobinbene", CoinbeneExchange.class);
        exchangeSpec.put("coindirect", CoindirectExchange.class);
        exchangeSpec.put("coinegg", CoinEggExchange.class);
        exchangeSpec.put("coinfloor", CoinfloorExchange.class);
        exchangeSpec.put("coingi", CoingiExchange.class);
        exchangeSpec.put("coinmarketcap", CoinMarketCapExchange.class);
        exchangeSpec.put("coinmate", CoinmateExchange.class);
        exchangeSpec.put("coinone", CoinoneExchange.class);
        exchangeSpec.put("coinsuper", CoinsuperExchange.class);
        exchangeSpec.put("cryptonit", CryptonitExchange.class);
        exchangeSpec.put("cryptopia", CryptopiaExchange.class);
        exchangeSpec.put("dsx", DSXExchange.class);
        exchangeSpec.put("exmo", ExmoExchange.class);
        exchangeSpec.put("exx", EXXExchange.class);
        exchangeSpec.put("fcoin", FCoinExchange.class);
        exchangeSpec.put("gateio", GateioExchange.class);
        exchangeSpec.put("gatecoin", GatecoinExchange.class);
        exchangeSpec.put("gemini", GeminiExchange.class);
        exchangeSpec.put("hitbtc", HitbtcExchange.class);
        exchangeSpec.put("huobi", HuobiExchange.class);
        exchangeSpec.put("idex", IdexExchange.class);
        exchangeSpec.put("independantreserve", IndependentReserveExchange.class);
        exchangeSpec.put("itbit", ItBitExchange.class);
        exchangeSpec.put("koineks", KoineksExchange.class);
        exchangeSpec.put("koinim", KoinimExchange.class);
        exchangeSpec.put("kraken", KrakenExchange.class);
        exchangeSpec.put("kucoin", KucoinExchange.class);
        exchangeSpec.put("kuna", KunaExchange.class);
        exchangeSpec.put("lakebtc", LakeBTCExchange.class);
        exchangeSpec.put("liqui", LiquiExchange.class);
        exchangeSpec.put("livecoin", LivecoinExchange.class);
        exchangeSpec.put("luno", LunoExchange.class);
        exchangeSpec.put("mercadobitcoin", MercadoBitcoinExchange.class);
        exchangeSpec.put("okcoin", OkCoinExchange.class);
        exchangeSpec.put("openexchangerates", OERExchange.class);
        exchangeSpec.put("paribu", ParibuExchange.class);
        exchangeSpec.put("paymium", PaymiumExchange.class);
        exchangeSpec.put("poloniex", PoloniexExchange.class);
        exchangeSpec.put("quadrigacx", QuadrigaCxExchange.class);
        exchangeSpec.put("quoine", QuoineExchange.class);
        exchangeSpec.put("ripple", RippleExchange.class);
        exchangeSpec.put("therocktrading", TheRockExchange.class);
        exchangeSpec.put("upbit", UpbitExchange.class);
        exchangeSpec.put("vaultoro", VaultoroExchange.class);
        exchangeSpec.put("vircurex", VircurexExchange.class);
        exchangeSpec.put("wex", WexExchange.class);
        exchangeSpec.put("yobit", YoBitExchange.class);
        exchangeSpec.put("zaif", ZaifExchange.class);
    }

    private MarketDataRegistry() {

    }

    static Set<String> getAllExchangeNames() {
        return exchangeSpec.keySet();
    }

    static Map<String, Exchange> buildMarketDataSources() {
        List<CompletableFuture<ExchangeListEntry>> exchangeThreadPool = new ArrayList<>();
        Map<String, Exchange> exchanges = new HashMap<>();

        for(Map.Entry e : exchangeSpec.entrySet()) {
            MarketDataRegistryWorker marketDataRegistryWorker = new MarketDataRegistryWorker((String)e.getKey(), (Class)e.getValue());
            exchangeThreadPool.add(CompletableFuture.supplyAsync(marketDataRegistryWorker));
        }

        CompletableFuture.allOf((CompletableFuture<?>[]) exchangeThreadPool.toArray(new CompletableFuture[exchangeThreadPool.size()])).join();

        for(CompletableFuture<ExchangeListEntry> exchangeThread : exchangeThreadPool) {
            ExchangeListEntry exchangeListEntry = null;

            try {
                exchangeListEntry = exchangeThread.get();
            } catch (InterruptedException e) {
                LOGGER.error(e.toString(), e);
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                LOGGER.error(e.toString(), e);
            }

            if(exchangeListEntry != null && exchangeListEntry.getExchange() != null) {
                exchanges.put(exchangeListEntry.getExchangeName(), exchangeListEntry.getExchange());
            }
        }

        return exchanges;
    }
}
