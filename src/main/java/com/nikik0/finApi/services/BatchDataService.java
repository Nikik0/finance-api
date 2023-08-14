package com.nikik0.finApi.services;

import com.nikik0.finApi.entities.StockEntity;
import com.nikik0.finApi.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.List;

@Service
@Slf4j
public class BatchDataService {

    private final StockRepository dataRepository;

    @Autowired
    public BatchDataService(StockRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

//    public void saveDataInBatches(List<StockEntity> dataList, int batchSize) {
//        dataRepository.saveAll(dataList);
////        for (int i = 0; i < dataList.size(); i += batchSize) {
////            int endIndex = Math.min(i + batchSize, dataList.size());
////            List<StockEntity> batch = dataList.subList(i, endIndex);
////            dataRepository.saveAll(batch);
////        }
//    }

    public void saveDataInBatches(List<StockEntity> stockEntities) {
        //log.info("going to save " + stockEntities.size());
        log.info("saving batch size of " + stockEntities.size());
        if (stockEntities.size() != 100 ){
            log.warn("starting saving last batch");
            dataRepository.count().flatMap(
                    res -> {
                        if(res > 10000)
                            log.warn("end test");
                        return Mono.just(res);
                    }
            ).subscribe();
            log.warn("end time for test");

        }
        dataRepository.saveAll(stockEntities).subscribe();
    }

    public void countSavedEntities() {
        dataRepository.count().flatMap(
                result -> {
                    log.info("there are " + result + " entities in db");
                    return Mono.just(result);
                }
        ).subscribe();
    }
}