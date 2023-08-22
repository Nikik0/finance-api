package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.StockEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StockRepository extends R2dbcRepository<StockEntity, Long> {
    @Query("SELECT * FROM stocks s WHERE s.change_percent IS NOT NULL ORDER BY ABS(s.change_percent) DESC LIMIT $1")
    Flux<StockEntity> findMostChangedStocks(Integer limit);
    @Query("SELECT * FROM stocks s WHERE s.volume IS NOT NULL ORDER BY s.volume DESC LIMIT $1")
    Flux<StockEntity> findHighestVolumeStocks(Integer limit);
}
