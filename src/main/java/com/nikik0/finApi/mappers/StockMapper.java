package com.nikik0.finApi.mappers;

import com.nikik0.finApi.dtos.StockDto;
import com.nikik0.finApi.entities.StockEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockEntity mapDtoToEntity(StockDto stockDto);
    StockDto mapEntityToDto(StockEntity stockEntity);
}
