package com.nikik0.finApi.finRepositories;

import com.nikik0.finApi.entities.SymbolFinEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends R2dbcRepository<SymbolFinEntity, Long> {

}
