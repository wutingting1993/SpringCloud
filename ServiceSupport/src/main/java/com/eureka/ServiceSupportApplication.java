package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class ServiceSupportApplication {
	@HystrixProperty(name = "hystrix.command.default.execution.isolation.strategy", value = "thread")
	public static void main(String[] args) {
		SpringApplication.run(ServiceSupportApplication.class, args);
	}
}
