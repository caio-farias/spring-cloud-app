package com.av3.springcloudappuserdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAppUserdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAppUserdataApplication.class, args);
	}

	@Bean
	public Sampler alwaysSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
