package com.nikik0.finApi.controllers;

import com.nikik0.finApi.apiProxy.ExternalApiProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
                    return Mono.just(response);
                }
        ).subscribe();
    }
}
