package com.valletta.pass.job.pass;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AddPassesJobConfig {

    // @EnableBatchProcessing으로 인해 Bean으로 제공된 JobBuilderFactory, StepBuilderFactory
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AddPassesTasklet addPassesTasklet;

    @Bean
    public Job addPassesJob() {
        return this.jobBuilderFactory.get("addPassesJob")
            .start(addPassesStep())
            .build();
    }

    private Step addPassesStep() {
        return this.stepBuilderFactory.get("addPassesStep")
            .tasklet(addPassesTasklet)
            .build();
    }
}