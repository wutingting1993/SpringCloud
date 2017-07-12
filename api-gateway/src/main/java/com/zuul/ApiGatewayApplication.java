package com.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.zuul.comment.AccessFilter;

@SpringCloudApplication
@EnableZuulProxy
public class ApiGatewayApplication {

	@Bean
	AccessFilter configAccessFilter() {
		return new AccessFilter("pre");
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
