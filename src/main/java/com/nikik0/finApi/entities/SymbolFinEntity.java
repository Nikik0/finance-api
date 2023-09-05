package com.nikik0.finApi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("company")
public class SymbolFinEntity {
    Long id;
    String description;
    String displaySymbol;
    String symbol;
    String type;
}
