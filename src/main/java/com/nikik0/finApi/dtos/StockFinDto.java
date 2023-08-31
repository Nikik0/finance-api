package com.nikik0.finApi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockFinDto {
    String currency;
    String description;
    String displaySymbol;
    String figi;
    String mic;
    String symbol;
    String type;
}
