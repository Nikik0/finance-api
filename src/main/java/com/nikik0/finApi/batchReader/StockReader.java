package com.nikik0.finApi.batchReader;


import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.entities.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class StockReader implements ItemReader<StockEntity> {
    private final ExternalApiProxy api;
    private Queue<StockEntity> data;


    public StockReader(ExternalApiProxy api) {
        this.api = api;
        subscribeOnStartUp();
    }

    @Override
    public StockEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return data.poll();
    }

    private void subscribeOnStartUp(){
        data = new ArrayDeque<>(20);
        api.performCallToExternalApi("/ref-data/symbols", "", StockEntity.class, HttpMethod.GET).subscribe(this::addToQueue);
    }

    private void addToQueue(Object stock) {
        data.add((StockEntity) stock);
    }

}
