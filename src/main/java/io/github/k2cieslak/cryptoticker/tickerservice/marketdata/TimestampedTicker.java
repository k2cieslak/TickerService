package io.github.k2cieslak.cryptoticker.tickerservice.marketdata;

import org.knowm.xchange.dto.marketdata.Ticker;

public class TimestampedTicker  {
    private long timestamp;
    private Ticker ticker;

    public TimestampedTicker(long timestamp, Ticker ticker) {
        this.timestamp = timestamp;
        this.ticker = ticker;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Ticker getTicker() {
        return ticker;
    }
}
