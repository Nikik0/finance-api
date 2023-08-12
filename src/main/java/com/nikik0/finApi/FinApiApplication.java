package com.nikik0.finApi;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinApiApplication.class, args);
	}

}
