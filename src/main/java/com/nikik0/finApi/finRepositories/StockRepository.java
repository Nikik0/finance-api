package com.nikik0.finApi.finRepositories;

import com.nikik0.finApi.entities.StockFinEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends R2dbcRepository<StockFinEntity, Long> {

}
