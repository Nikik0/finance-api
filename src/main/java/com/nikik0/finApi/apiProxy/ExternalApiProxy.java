package com.nikik0.finApi.apiProxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;
import reactor.util.retry.Retry;


import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.function.Function;

@Service
@Slf4j
public class ExternalApiProxy {

    private WebClient webClient;

    private final String baseUrl = "https://cloud.iexapis.com";

    private final String version = "stable";

    private String token = "pk_48c37b6c4d5541c8a9c38671f4922c88";

    public ExternalApiProxy() {
        this.webClient = WebClient.builder().baseUrl(baseUrl + "/" + version + "/").build();
    }

    public <T> Flux<T> performCallToExternalApi(String prefixUri,String uri, Class T, HttpMethod requestMethod){
        log.info("call started to " + buildUri(prefixUri, uri));
        return this.webClient.method(requestMethod)
                .uri(buildUri(prefixUri, uri))
                .retrieve()
                .bodyToFlux(T)
                .retryWhen(Retry.fixedDelay(1000, Duration.ofSeconds(10)))
                .doOnError(
                        throwable -> log.error("Error occurred while processing response " + throwable.toString())
                );
    }

    private String buildUri(String prefix, String postfix){
        return prefix + "?token=" + token + postfix;
    }


    public Flux<Object> call(){
        return this.webClient.get().uri("tops?token=" + token + "&symbols=aapl").retrieve()
                .bodyToFlux(Object.class);
    }
}
