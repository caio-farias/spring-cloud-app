package com.av3.springcloudappapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAppApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAppApiGatewayApplication.class, args);
	}

}
