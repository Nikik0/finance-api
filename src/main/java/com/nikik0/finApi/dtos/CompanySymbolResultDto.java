package com.nikik0.finApi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CompanySymbolResultDto {
    @JsonProperty("count")
    Long count;
    @JsonProperty("result")
    List<SymbolFinDto> result;
}
