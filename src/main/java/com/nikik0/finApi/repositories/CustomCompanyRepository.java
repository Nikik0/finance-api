package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.CompanyEntity;

import java.util.List;

public interface CustomCompanyRepository {
    void saveAll(List<CompanyEntity> entities);
}
