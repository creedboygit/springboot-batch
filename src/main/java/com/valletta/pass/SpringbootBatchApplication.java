package com.valletta.pass;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class SpringbootBatchApplication {

	@Bean
	public Step passStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("passStep", jobRepository)
			.tasklet((stepContribution, chunkContext) -> {
				System.out.println("Execute PassStep");
				return RepeatStatus.FINISHED;
			}, transactionManager).build();
	}

	@Bean
	public Job passJob(JobRepository jobRepository, Step passStep) {
		return new JobBuilder("passJob", jobRepository)
			.start(passStep)
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBatchApplication.class, args);
	}

}
