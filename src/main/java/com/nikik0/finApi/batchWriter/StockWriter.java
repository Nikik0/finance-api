package com.nikik0.finApi.batchWriter;

import com.nikik0.finApi.entities.StockEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
@Slf4j
public class StockWriter implements ItemWriter<StockEntity> {
    @Override
    public void write(Chunk<? extends StockEntity> chunk) throws Exception {
        log.info("Now writing stocks " + chunk.getItems());
    }
}
