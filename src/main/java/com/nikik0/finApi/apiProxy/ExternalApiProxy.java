package com.nikik0.finApi.apiProxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.http.HttpRequest;

@Service
@Slf4j
public class ExternalApiProxy {
    private final WebClient webClient;

    private final String baseUrl = "https://cloud.iexapis.com";

    private final String version = "stable";

    private String token = "pk_f95e6f2d71c34284bcd2a567d5de7fb1";


    public ExternalApiProxy() {
        this.webClient = WebClient.builder().baseUrl(baseUrl + "/" + version + "/").build();
    }


    public <T> Flux<T> performCallToExternalApi(String uri, Class T, HttpMethod requestMethod){
        return this.webClient.method(requestMethod)
                .uri("tops?token=" + token + uri)
                .retrieve()
                .bodyToFlux(T)
                .doOnError(
                        throwable -> log.error("Error occurred while processing response " + throwable.toString())
                );
    }



    public Flux<Object> call(){
        return this.webClient.get().uri("tops?token=" + token + "&symbols=aapl").retrieve()

                .bodyToFlux(Object.class);
    }
}
