package com.nikik0.finApi.controllers;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import com.nikik0.finApi.entities.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("fin")
@RequiredArgsConstructor
@Slf4j
public class ApiRequestController {
    private final ExternalApiProxy externalApiProxy;

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
    @RequestMapping("/test2")
    public void testCall2(){
        externalApiProxy.performCallToExternalApi("tops","&symbols=aapl", Object.class, HttpMethod.GET).flatMap(
                response ->{
                    log.info("received response " + response);
                    return Mono.just(response);
                }
        ).subscribe();
    }

    @RequestMapping("/test3")
    public void testCall3(){
        Queue<Object> responses = new ArrayDeque<>();
        externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", StockEntity.class, HttpMethod.GET).flatMap(
                response -> {
                    responses.add(response);
                    //log.info("received " + response);
                    return Mono.just(response);
                }
        ).buffer(20).subscribe(this::postProcessing);
    }

    private int counter = 0;

    private void postProcessing(List<Object> result) {
        counter++;
        log.info("received data part " + counter + " size of " + result.size());
        log.info("first element in batch " + result.get(0));
    }

    @RequestMapping("/test4")
    public Mono<Object> testCall4(){
        return externalApiProxy.performCallToExternalApi("/ref-data/symbols", "", StockEntity.class, HttpMethod.GET).flatMap(
                response -> {
                    log.info("response class " + response.getClass());
                    log.info("response data " + response);
                    //log.info("received " + response);
                    return Mono.just(response);
                }
        ).last();
    }

}
