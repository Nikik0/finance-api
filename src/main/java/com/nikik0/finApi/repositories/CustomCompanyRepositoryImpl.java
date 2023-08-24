package com.nikik0.finApi.repositories;

import com.nikik0.finApi.entities.CompanyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomCompanyRepositoryImpl implements CustomCompanyRepository{
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    @Override
    public void saveAll(List<CompanyEntity> entities) {
        entities.forEach(stock -> r2dbcEntityTemplate.insert(stock)
                .doOnSubscribe(x -> log.info("started saving batch"))
                .doFinally(x -> log.info("finished saving batch"))
                .doOnError(throwable -> log.error("error occurred while saving the company {}", throwable.getMessage()))
                .subscribe()
        );
//        entities.stream().map(
//                r2dbcEntityTemplate::insert
//        ).collect(Collectors.toList());
    }
}
