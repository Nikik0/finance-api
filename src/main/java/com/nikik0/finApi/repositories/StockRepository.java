package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.StockEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends R2dbcRepository<StockEntity, Long> {
}