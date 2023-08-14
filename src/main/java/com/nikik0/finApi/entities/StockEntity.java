package com.nikik0.finApi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("stocks")
public class StockEntity {
    private String symbol;
    private String exchange;
    @Column("exchange_suffix")
    private String exchangeSuffix;
    @Column("exchange_name")
    private String exchangeName;
    @Column("exchange_segment")
    private String exchangeSegment;
    @Column("exchange_segment_name")
    private String exchangeSegmentName;
    private String name;
    private String date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    @Column("is_enabled")
    private String isEnabled;
    private String figi;
    private String cik;
    private String lei;

}
