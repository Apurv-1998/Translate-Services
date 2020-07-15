package com.mobileapplication.exposys.translate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.codahale.metrics.MetricRegistry;

@SpringBootApplication
@EnableCaching
public class TranslateServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslateServicesApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public MetricRegistry metricRegistry() {
		return new MetricRegistry();
	}
}
