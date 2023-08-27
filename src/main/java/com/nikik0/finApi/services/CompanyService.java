package com.nikik0.finApi.services;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.dtos.CompanyDto;
import com.nikik0.finApi.dtos.StockDto;
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


    public void test(){
        executorService.submit(
                () -> {
                    companyRepository.count().subscribe();
                }
        );
    }
    public void getCompanies1(){
        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET).flatMap(
                response -> {
                    //log.info("comp " + response);
                    //log.info("received " + response);
                    companyRepository.save(companyMapper.mapFromDtoToEntity((CompanyDto) response)).subscribe();
                    return Mono.just((CompanyDto) response);
                }
        ).doOnSubscribe(x -> log.info("started saving"))
                .doFinally(x -> log.info("saving finished"))
                .subscribe(this::createTaskForGettingStocks);
    }
    public void getCompanies4(){
        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET)
                .buffer(1000)
                .flatMap(
                        response -> {
                            //log.info("comp " + response);
                            //log.info("received " + response);
                            //log.info("company input list size is " + response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()).size());
                            companyRepository.saveAll(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList())).subscribe();
                            return Mono.just(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()));
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

    public void getCompanies2(){
        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET)
                .buffer(1000)
                .flatMap(
                        response -> {
                            //log.info("comp " + response);
                            //log.info("received " + response);
                            //log.info("company input list size is " + response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()).size());
                            customCompanyRepository.saveAll(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()));
                            return Mono.just(response.stream().map(x -> companyMapper.mapFromDtoToEntity((CompanyDto) x)).collect(Collectors.toList()));
                        }
                ).doOnSubscribe(x -> log.info("started saving"))
                .doFinally(x -> log.info("saving finished"))
                .subscribe();
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
