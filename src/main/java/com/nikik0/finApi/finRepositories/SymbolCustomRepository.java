package com.nikik0.finApi.finRepositories;

import com.nikik0.finApi.entities.SymbolFinEntity;

import java.util.List;

public interface SymbolCustomRepository {
    void saveAll(List<SymbolFinEntity> entities);
}
