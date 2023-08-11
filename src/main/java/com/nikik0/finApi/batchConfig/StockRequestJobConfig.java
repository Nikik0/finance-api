package com.nikik0.finApi.batchConfig;

import com.nikik0.finApi.batchProcessor.StockProcessor;
import com.nikik0.finApi.batchReader.StockReader;
import com.nikik0.finApi.entities.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StockRequestJobConfig {
    private final JobBuilder jobBuilder;
    private final StepBuilder stepBuilder;
    private final PlatformTransactionManager platformTransactionManager;
    private final StockReader stockReader;
    private final ItemProcessor stockProcessor;
    private final ItemWriter stockWriter;


    @Bean
    public Job stockRequestJob(){
        return jobBuilder.start(taskletStep()).build();
    }
    @Bean
    public Step taskletStep(){
        return stepBuilder.tasklet(tasklet(), platformTransactionManager).build();
    }
    @Bean
    public Tasklet tasklet() {
        return ((contribution, chunkContext) ->
                RepeatStatus.FINISHED);
    }
    @Bean
    public Step step() throws Exception {
        return stepBuilder.chunk(20, platformTransactionManager)
                .reader(stockReader)
                .processor(stockProcessor)
                .writer(stockWriter)
                .build();
    }

}
