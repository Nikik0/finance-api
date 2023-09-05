package com.nikik0.finApi.finMappers;

import com.nikik0.finApi.dtos.StockFinDto;
import com.nikik0.finApi.entities.StockFinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockFinMapper {
    StockFinEntity mapFromDtoToEntity(StockFinDto stockFinDto);
    StockFinDto mapFromEntityToDto(StockFinEntity stockFinEntity);
}
