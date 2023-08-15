package com.nikik0.finApi.mappers;

import com.nikik0.finApi.dtos.CompanyDto;
import com.nikik0.finApi.entities.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto mapFromEntityToDto(CompanyEntity companyEntity);
    CompanyEntity mapFromDtoToEntity(CompanyDto companyDto);
}
