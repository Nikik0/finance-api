package com.nikik0.finApi.services;

import com.nikik0.finApi.entities.StockEntity;
import com.nikik0.finApi.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
        dataRepository.saveAll(stockEntities);
    }
}