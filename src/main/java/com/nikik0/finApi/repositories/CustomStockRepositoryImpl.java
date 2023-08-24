package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomStockRepositoryImpl implements CustomStockRepository{
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    @Override
    public void saveAll(List<StockEntity> entities) {
        entities.forEach(stock -> r2dbcEntityTemplate.insert(stock)
                .doOnError(throwable -> log.error("error occurred while saving the stock {}", throwable.getMessage()))
                .subscribe()
        );
    }

    @Override
    public void save(StockEntity stock) {
        r2dbcEntityTemplate.insert(stock).subscribe();
    }
}
