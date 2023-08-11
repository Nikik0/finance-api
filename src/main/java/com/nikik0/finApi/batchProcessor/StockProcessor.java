package com.nikik0.finApi.batchProcessor;

import com.nikik0.finApi.entities.StockEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class StockProcessor implements ItemProcessor<StockEntity, StockEntity> {
    @Override
    public StockEntity process(StockEntity item) throws Exception {
        log.info("processing stock " + item);
        return item;
    }
}
