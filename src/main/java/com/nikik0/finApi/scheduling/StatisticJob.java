package com.nikik0.finApi.scheduling;

import com.nikik0.finApi.services.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticJob {
    private final StockService stockService;

    @Scheduled(fixedRate = 1000*5)
    public void highestVolumeStocks() {
        CompletableFuture.runAsync(
                () -> stockService.getHighestVolumeStocks(5).subscribe(stock ->
                        log.info("highest volume stock {} from company {}", stock.getSymbol(), stock.getCompanyName())
                )
        ).join();
    }

    @Scheduled(fixedRate = 1000*5)
    public void mostChangedStocks(){
        CompletableFuture.runAsync(() -> stockService.getMostChangedStocks(5).subscribe(
                x -> log.info("most changed stock {} by company {} was changed by {}%", x.getSymbol(), x.getCompanyName(), x.getChangePercent())
        )).join();
    }
}
