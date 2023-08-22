package com.nikik0.finApi.services;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.dtos.CompanyDto;
import com.nikik0.finApi.dtos.StockDto;
import com.nikik0.finApi.entities.StockEntity;
import com.nikik0.finApi.mappers.StockMapper;
import com.nikik0.finApi.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockMapper stockMapper;
    private final StockRepository stockRepository;
    private final ExternalApiProxy externalApiProxy;

    public void getStocks1(CompanyDto companyDto){

        log.info("called for " + companyDto.getSymbol() + " at " + LocalDateTime.now());
    }

    public Mono<Long> getTotalCount(){
        return stockRepository.count();
    }
    public void getStocks(CompanyDto companyDto){
        externalApiProxy.performCallToExternalApi("/stock/" + companyDto.getSymbol() + "/quote","", StockDto.class, HttpMethod.GET).flatMap(
                        response -> {
                            log.info("received " + response);
                            return Mono.just((StockDto) response);
                        }
                ).onErrorComplete().map(stockMapper::mapDtoToEntity)
                .buffer(100)
                .subscribe(this::saveStocks);
    }
    private void saveStocks(List<StockEntity> stockEntities){
        stockRepository.saveAll(stockEntities).subscribe();
    }

    public Flux<StockEntity> getMostChangedStocks(Integer amount){
        return stockRepository.findMostChangedStocks(amount);
    }
    public Flux<StockEntity> getHighestVolumeStocks(Integer amount){
        return stockRepository.findHighestVolumeStocks(amount);
    }
}
