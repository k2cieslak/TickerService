package io.github.k2cieslak.cryptoticker.tickerservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Ticker implements Serializable {
    private long timestamp;
    private BigDecimal ask;
    private BigDecimal bid;
    private BigDecimal last;
}
