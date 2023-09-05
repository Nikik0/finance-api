package com.nikik0.finApi.finRepositories;

import com.nikik0.finApi.entities.StockFinEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockCustomRepositoryImpl implements StockCustomRepository{
    private final R2dbcEntityTemplate entityTemplate;
    @Override
    public void saveAll(List<StockFinEntity> entityList) {
        entityList.forEach(entityTemplate::insert);
    }
}
