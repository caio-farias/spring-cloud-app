package com.av3.springcloudapptasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAppTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAppTasksApplication.class, args);
	}

	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
