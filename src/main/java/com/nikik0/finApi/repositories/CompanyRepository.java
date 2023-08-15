package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.CompanyEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends R2dbcRepository<CompanyEntity, Long> {
}
