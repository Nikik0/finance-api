package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.StockEntity;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CustomStockRepository {
    void saveAll(List<StockEntity> stocks);
    void save(StockEntity stock);
}
