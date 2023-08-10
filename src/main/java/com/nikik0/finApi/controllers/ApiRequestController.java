package com.nikik0.finApi.controllers;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;

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
        externalApiProxy.performCallToExternalApi("&symbols=aapl", Object.class, HttpMethod.GET).flatMap(
                response ->{
                    log.info("received response " + response);
                    return Mono.just(response);
                }
        ).subscribe();
    }



}
