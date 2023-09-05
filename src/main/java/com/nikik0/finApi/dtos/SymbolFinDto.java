package com.nikik0.finApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SymbolFinDto {
    @JsonProperty("description")
    String description;
    @JsonProperty("displaySymbol")
    String displaySymbol;
    @JsonProperty("symbol")
    String symbol;
    @JsonProperty("type")
    String type;
}
