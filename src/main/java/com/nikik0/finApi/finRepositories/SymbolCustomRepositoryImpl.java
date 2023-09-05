package com.nikik0.finApi.finRepositories;

import com.nikik0.finApi.entities.SymbolFinEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SymbolCustomRepositoryImpl implements SymbolCustomRepository{

    private final R2dbcEntityTemplate entityTemplate;

    @Override
    public void saveAll(List<SymbolFinEntity> entities) {
        entities.forEach(entityTemplate::insert);
    }
}
