package com.rongdu.eloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WjEloanStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WjEloanStorageApplication.class, args);
	}

}
