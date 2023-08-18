package com.nikik0.finApi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("stocks")
public class StockEntity {
    Long id;
    @Column("avg_total_volume")
    Long avgTotalVolume;
    @Column("calculation_price")
    String calculationPrice; //todo might be worth reworking to enum
    double change;
    @Column("change_percent")
    double changePercent;
    double close;
    @Column("close_source")
    String closeSource;
    @Column("close_time")
    Long closeTime;
    @Column("company_name")
    String companyName;
    String currency;
    @Column("delayed_price")
    double delayedPrice;
    @Column("delayed_price_time")
    Long delayedPriceTime;
    @Column("extended_change")
    double extendedChange;
    @Column("extended_change_percent")
    double extendedChangePercent;
    @Column("extended_price")
    double extendedPrice;
    @Column("extended_price_time")
    Long extendedPriceTime;
    double high;
    @Column("high_source")
    String highSource;
    @Column("high_time")
    Long highTime;
    @Column("iex_ask_price")
    double iexAskPrice;
    @Column("iex_ask_size")
    double iexAskSize;
    @Column("iex_bid_price")
    double iexBidPrice;
    @Column("iex_bid_size")
    double iexBidSize;
    @Column("iex_close")
    double iexClose;
    @Column("iex_close_time")
    Long iexCloseTime;
    @Column("iex_last_updated")
    Long iexLastUpdated;
    @Column("iex_market_percent")
    double iexMarketPercent;
    @Column("iex_open")
    double iexOpen;
    @Column("iex_open_time")
    Long iexOpenTime;
    @Column("iex_realtime_price")
    double iexRealtimePrice;
    @Column("iex_realtime_size")
    double iexRealtimeSize;
    @Column("iex_volume")
    Long iexVolume;
    @Column("last_trade_time")
    Long lastTradeTime;
    @Column("latest_price")
    double latestPrice;
    @Column("latest_source")
    String latestSource;
    @Column("latest_time")
    String latestTime;
    @Column("latest_update")
    Long latestUpdate;
    @Column("latest_volume")
    Long latestVolume;
    double low;
    @Column("low_source")
    String lowSource;
    @Column("low_time")
    Long lowTime;
    @Column("market_cap")
    Long marketCap;
    @Column("odd_lot_delayed_price")
    double oddLotDelayedPrice;
    @Column("odd_lot_delayed_price_time")
    Long oddLotDelayedPriceTime;
    double open;
    @Column("open_time")
    Long openTime;
    @Column("open_source")
    String openSource;
    @Column("pe_ratio")
    double peRatio;
    @Column("previous_close")
    double previousClose;
    @Column("previous_volume")
    Long previousVolume;
    @Column("primary_exchange")
    String primaryExchange;
    String symbol;
    Long volume;
    @Column("week52_high")
    double week52High;
    @Column("week52_low")
    double week52Low;
    @Column("ytd_change")
    double ytdChange;
    @Column("is_US_market_open")
    boolean isUSMarketOpen;
}
