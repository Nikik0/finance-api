package com.nikik0.finApi.apiProxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
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

    private final String baseUrl1 = "https://cloud.iexapis.com";
    private final String baseUrl = "https://finnhub.io/api";

    private final String version1 = "stable";
    private final String version = "v1";

    private String token1 = "pk_48c37b6c4d5541c8a9c38671f4922c88";
    private String token = "cjo0lg9r01qmd05u3gu0cjo0lg9r01qmd05u3gug";


    public ExternalApiProxy() {
        final Integer codecInMemSize = 16*1024*1024;
        final ExchangeStrategies exchangeStrategies = ExchangeStrategies
                .builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(codecInMemSize))
                .build();
        this.webClient = WebClient.builder().exchangeStrategies(exchangeStrategies).baseUrl(baseUrl + "/" + version + "/").build();
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

    public <T> Flux<T> performCallToExternalApi1(String prefixUri,String uri, Class T, HttpMethod requestMethod){
        log.info("call started to " + buildUri(prefixUri, uri));
        this.webClient.method(requestMethod)
                .uri(buildUri(prefixUri, uri))
                .retrieve()
                .bodyToMono(T)
                //.retryWhen(Retry.fixedDelay(1000, Duration.ofSeconds(10)))
                .flatMap(
                        x-> {
                            log.info(" Received " + x.toString());
                            return Mono.just(x);
                        }
                )
                .doOnError(
                        throwable -> log.error("Error occurred while processing response " + throwable.toString())
                ).subscribe();
        return Flux.empty();
    }

    private String buildUri(String prefix, String postfix){
        if (!postfix.isEmpty()) postfix = "&" + postfix;
        return prefix + "?token=" + token + postfix;
    }


    public Flux<Object> call(){
        return this.webClient.get().uri("tops?token=" + token + "&symbols=aapl").retrieve()
                .bodyToFlux(Object.class);
    }
}
