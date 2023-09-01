package com.nikik0.finApi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompanySymbolResultDto {
    Long count;
    List<CompanyFinDto> result;
}
