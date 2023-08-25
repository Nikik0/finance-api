package com.nikik0.finApi.controllers;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.dtos.CompanyDto;
import com.nikik0.finApi.dtos.StockDto;
import com.nikik0.finApi.entities.CompanyEntity;
import com.nikik0.finApi.services.CompanyService;
import com.nikik0.finApi.services.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("fin")
@RequiredArgsConstructor
@Slf4j
public class ApiRequestController {
    private final ExternalApiProxy externalApiProxy;
    private final CompanyService companyService;
    private final StockService stockService;

    @RequestMapping("/test")
    public void testCall(){
        externalApiProxy.call().flatMap(response ->
                {
                    log.info("received response " + response);
                    log.info("received class " + response.getClass());
                    return Mono.just(response);
                }
        ).subscribe();
    }

    @RequestMapping("/companies/total")
    public void getCompaniesTotalCount(){
        companyService.getTotalCount().flatMap(total -> {
                    log.info("total amount of company entities " + total);
                    return Mono.just(total);
                }
        ).subscribe();
    }

    @RequestMapping("/stocks/total")
    public void getStocksTotalCount() {
        stockService.getTotalCount().flatMap(total -> {
                    log.info("total amount of stocks entities " + total);
                    return Mono.just(total);
                }
        ).subscribe();
    }

//    @RequestMapping("/test3")
//    public void testCall3(){
//        Queue<Object> responses = new ArrayDeque<>();
//        log.warn("started test");
//        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET).flatMap(
//                response -> {
//                    responses.add(response);
//                    //log.info("received " + response);
//                    return Mono.just((CompanyDto) response);
//                }
//        ).buffer(100).subscribe(batchDataService::saveDataInBatches);
//    }

    @RequestMapping("/stocks/{company}")
    public Flux<StockDto> getStocks(@PathVariable String company){
        return externalApiProxy.performCallToExternalApi("/stock/" + company + "/quote","", StockDto.class, HttpMethod.GET).flatMap(
                response -> {
                    log.info("received " + response);
                    return Mono.just((StockDto) response);
                }
        );
    }

    @RequestMapping("/companies")
    public Flux<CompanyDto> getCompanies(){
        return externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyDto.class, HttpMethod.GET).flatMap(
                response -> {
                    //log.info("received " + response);
                    return Mono.just((CompanyDto) response);
                }
        );
    }

    @RequestMapping("/test4")
    public Mono<Object> testCall4(){
        return externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", CompanyEntity.class, HttpMethod.GET).flatMap(
                response -> {
                    log.info("response class " + response.getClass());
                    log.info("response data " + response);
                    //log.info("received " + response);
                    return Mono.just(response);
                }
        ).last();
    }


}
