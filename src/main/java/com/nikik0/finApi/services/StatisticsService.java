package com.nikik0.finApi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final StockService stockService;

    @Scheduled(fixedRate = 1000*5)
    public void highestVolumeStocks() {
        log.info("called stats method");
        stockService.getHighestVolumeStocks().flatMap(res -> {
                    log.info("highest stocks are  from " + res.getCompanyName() + "; volume is " + res.getVolume() + "; symbol is " + res.getSymbol());
                    return Mono.just(res);
                }
        ).subscribe();
    }
}
