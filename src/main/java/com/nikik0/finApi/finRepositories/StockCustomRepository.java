package com.nikik0.finApi.finRepositories;

import com.nikik0.finApi.entities.StockFinEntity;

import java.util.List;

public interface StockCustomRepository {
    void saveAll(List<StockFinEntity> entityList);
}
