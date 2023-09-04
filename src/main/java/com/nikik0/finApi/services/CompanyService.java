package com.nikik0.finApi.services;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.dtos.*;
import com.nikik0.finApi.entities.StockEntity;
import com.nikik0.finApi.mappers.CompanyMapper;
import com.nikik0.finApi.mappers.StockMapper;
import com.nikik0.finApi.repositories.CompanyRepository;
import com.nikik0.finApi.repositories.CustomCompanyRepositoryImpl;
import com.nikik0.finApi.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final StockRepository stockRepository;
    private final CustomCompanyRepositoryImpl customCompanyRepository;
    private final ExternalApiProxy externalApiProxy;
    private final StockService stockService;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final CompanyMapper companyMapper;
    private final StockMapper stockMapper;
    @Value("${settings.batchSize}")
    private Long batchSize;


    public void getCompaniesFin1(){
        externalApiProxy.performCallToExternalApi("stock/symbol", "exchange=US", StockFinDto.class, HttpMethod.GET)
                .subscribe(res ->
                        log.info("received {}", ((StockFinDto) res).getSymbol()));
    }

    public void getCompaniesFin(){
        externalApiProxy.performCallToExternalApi("search", "q=", CompanySymbolResultDto.class, HttpMethod.GET)
                .flatMap(result -> Flux.just(((CompanySymbolResultDto) result).getResult()))
                //.buffer(1000)
                .subscribe(res ->
                        log.info("received {}", res));//((CompanySymbolResultDto) res).getResult().size()));
    }
    public void test(){
        executorService.submit(
                () -> {
                    companyRepository.count().subscribe();
                }
        );
    }
    public void getCompanies4(){
        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET)
                .buffer(1000)
                .flatMap(
                        response -> {

                            //log.info("comp " + response);
                            //log.info("received " + response);
                            //log.info("company input list size is " + response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()).size());
                            //companyRepository.saveAll(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList())).subscribe();
                            return Mono.just(companyRepository.saveAll(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList())));
                            //return Mono.just(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()));
                        }
                ).doOnSubscribe(x -> log.info("started saving"))
                .doFinally(x -> log.info("saving finished"))
                .subscribe();
        //.subscribe(this::createTaskForGettingStocks);
    }

    public void getCompanies(){
        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET)
                .buffer(1000)
                .subscribe(list -> companyRepository.saveAll(
                        list.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList())).subscribe()
                );

        //.subscribe(this::createTaskForGettingStocks);
    }

    private Integer i = 0;

    private void createTaskForGettingStocks(CompanyDto companyDto) {
        //log.info("task submitted to exec service " + i);
        i++;
        executorService.submit(
                () -> stockService.getStocks1(companyDto)
        );
    }

    public Mono<Long> getTotalCount(){
        return companyRepository.count();
    }
//    public void getStocks1(CompanyDto companyDto){
//
//        log.info("called for " + companyDto.getSymbol() + " at " + LocalDateTime.now());
//    }

//    public void getStocks(CompanyDto companyDto){
//        externalApiProxy.performCallToExternalApi("/stock/" + companyDto.getSymbol() + "/quote","", StockDto.class, HttpMethod.GET).flatMap(
//                response -> {
//                    log.info("received " + response);
//                    return Mono.just((StockDto) response);
//                }
//        ).onErrorComplete().map(stockMapper::mapDtoToEntity)
//                .buffer(100)
//                .subscribe(this::saveStocks);
//    }
//
//
//
//    private void saveStocks(List<StockEntity> stockEntities){
//        //stockRepository.saveAll(stockEntities).subscribe();
//    }
}
