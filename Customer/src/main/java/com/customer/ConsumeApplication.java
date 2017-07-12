package com.customer;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.customer.filter.HystrixRequestContextServletFilter;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
//@SpringCloudApplication
public class ConsumeApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HystrixRequestContextServletFilter hystrixRequestContextServletFilter() {
		return new HystrixRequestContextServletFilter();
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(hystrixRequestContextServletFilter());
		filter.setUrlPatterns(Arrays.asList(new String[] {"/*"}));
		filter.setName("hystrixRequestContextServletFilter");
		return filter;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumeApplication.class, args);
	}
}
