package com.nikik0.finApi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockFinEntity {
    Long id;
    String currency;
    String description;
    String displaySymbol;
    String figi;
    String mic;
    String symbol;
    String type;
}
