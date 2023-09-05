package com.nikik0.finApi.services;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.dtos.CompanySymbolResultDto;
import com.nikik0.finApi.dtos.StockFinDto;
import com.nikik0.finApi.dtos.SymbolFinDto;
import com.nikik0.finApi.finMappers.StockFinMapper;
import com.nikik0.finApi.finMappers.SymbolFinMapper;
import com.nikik0.finApi.finRepositories.SymbolCustomRepositoryImpl;
import com.nikik0.finApi.finRepositories.SymbolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SymbolService {
    private final ExternalApiProxy externalApiProxy;
    private final SymbolFinMapper symbolFinMapper;
    private final SymbolRepository symbolRepository;
    private final SymbolCustomRepositoryImpl symbolCustomRepository;

    @Scheduled(fixedRate = 1000 * 5)
    void retrieveSymbolsFromRemoteApi(){
        externalApiProxy.performCallToExternalApi("search", "q=", CompanySymbolResultDto.class, HttpMethod.GET)
                .flatMap(result -> Flux.fromIterable(((CompanySymbolResultDto) result).getResult()))
                .buffer(1000)
                .subscribe(this::saveBatch);
    }

    void saveBatch(List<SymbolFinDto> data){
        log.info("saving batch size of " + data.size() + " first item is " + data.get(0));
//        symbolCustomRepository.saveAll(data.stream()
//                .map(symbolFinMapper::mapFromDtoToEntity)
//                .collect(Collectors.toList()));
    }

}
