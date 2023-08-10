package com.nikik0.finApi.apiProxy;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExternalApiProxy {
    private final WebClient webClient;

    private final String baseUrl = "https://cloud.iexapis.com";

    private final String version = "stable";

    private String token = "pk_f95e6f2d71c34284bcd2a567d5de7fb1";


    public ExternalApiProxy() {
        this.webClient = WebClient.builder().baseUrl(baseUrl + "/" + version + "/").build();
    }

    public Flux<Object> call(){
        return this.webClient.get().uri("tops?token=" + token + "&symbols=aapl").retrieve().bodyToFlux(Object.class);
    }
}
