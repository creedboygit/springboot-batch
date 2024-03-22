package com.valletta.pass.job.pass;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AddPassesJobConfig {

    // @EnableBatchProcessing으로 인해 Bean으로 제공된 JobBuilderFactory, StepBuilderFactory
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AddPassesTasklet addPassesTasklet;

    @Bean
    public Job addPassesJob() {
        log.info("####### addPassesJob 실행");
        return this.jobBuilderFactory.get("addPassesJob")
//            .start(addPassesStep(null))
            .start(addPassesStep())
            .build();
    }

    //    private Step addPassesStep() {
//    @JobScope // Scope 추가(지연 초기화)
//    @Bean
//    public Step addPassesStep(@Value("#{jobParameters[requestDate]}") String requestDate) {
    public Step addPassesStep() {
        log.info("####### addPassesStep 실행");
        return this.stepBuilderFactory.get("addPassesStep")
            .tasklet(addPassesTasklet)
            .build();
    }
}
