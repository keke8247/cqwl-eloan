package com.rongdu.eloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayExamplesApplication.class, args);
	}

}
