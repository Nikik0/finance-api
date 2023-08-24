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
        entities.forEach(r2dbcEntityTemplate::insert);

//        entities.stream().map(
//                r2dbcEntityTemplate::insert
//        ).collect(Collectors.toList());
    }
}
