package com.nikik0.finApi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyFinDto {
    String description;
    String displaySymbol;
    String symbol;
    String type;
}
