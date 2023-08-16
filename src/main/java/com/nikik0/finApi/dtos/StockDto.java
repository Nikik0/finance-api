package com.nikik0.finApi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockDto {
    Long id;
    Long avgTotalVolume;
    String calculationPrice; //todo might be worth reworking to enum
    double change;
    double changePercent;
    double close;
    String closeSource;
    Long closeTime;
    String companyName;
    String currency;
    double delayedPrice;
    Long delayedPriceTime;
    double extendedChange;
    double extendedChangePercent;
    double extendedPrice;
    Long extendedPriceTime;
    double high;
    String highSource;
    Long highTime;
    double iexAskPrice;
    double iexAskSize;
    double iexBidPrice;
    double iexBidSize;
    double iexClose;
    Long iexCloseTime;
    Long iexLastUpdated;
    double iexMarketPercent;
    double iexOpen;
    Long iexOpenTime;
    double iexRealtimePrice;
    double iexRealtimeSize;
    Long iexVolume;
    Long lastTradeTime;
    double latestPrice;
    String latestSource;
    String latestTime;
    Long latestUpdate;
    Long latestVolume;
    double low;
    String lowSource;
    Long lowTime;
    Long marketCap;
    double oddLotDelayedPrice;
    Long oddLotDelayedPriceTime;
    double open;
    Long openTime;
    String openSource;
    double peRatio;
    double previousClose;
    Long previousVolume;
    String primaryExchange;
    String symbol;
    Long volume;
    double week52High;
    double week52Low;
    double ytdChange;
    boolean isUSMarketOpen;
}
