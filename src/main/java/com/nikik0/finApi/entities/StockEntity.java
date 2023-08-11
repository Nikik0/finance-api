package com.nikik0.finApi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
public class StockEntity {
    private String symbol;
    private String exchange;
    private String exchangeSuffix;
    private String exchangeName;
    private String exchangeSegment;
    private String exchangeSegmentName;
    private String name;
    private String date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private String isEnabled;
    private String figi;
    private String cik;
    private String lei;

}
