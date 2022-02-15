package com.av3.springcloudappeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudAppEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAppEurekaApplication.class, args);
	}

	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
