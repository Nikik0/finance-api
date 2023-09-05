package com.nikik0.finApi.finMappers;

import com.nikik0.finApi.dtos.StockFinDto;
import com.nikik0.finApi.dtos.SymbolFinDto;
import com.nikik0.finApi.entities.StockFinEntity;
import com.nikik0.finApi.entities.SymbolFinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SymbolFinMapper {
    SymbolFinDto mapFromEntityToDto(SymbolFinEntity symbolFinEntity);
    SymbolFinEntity mapFromDtoToEntity(SymbolFinDto symbolFinDto);
}
