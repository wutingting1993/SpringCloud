package com.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.zuul.comment.AccessFilter;
import com.zuul.comment.FilterConfigration;

@SpringCloudApplication
@EnableZuulProxy
@EnableConfigurationProperties(FilterConfigration.class)
public class ApiGatewayApplication {

	@Bean
	AccessFilter configAccessFilter() {
		return new AccessFilter("pre");
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public FilterLoader filterLoader(FilterConfigration filterConfigration) {
		FilterLoader filterLoader = FilterLoader.getInstance();
		filterLoader.setCompiler(new GroovyCompiler());
		FilterFileManager.setFilenameFilter(new GroovyFileFilter());
		try {
			FilterFileManager.init(filterConfigration.getInterval(), filterConfigration.getRoot() + "/pre",
				filterConfigration.getRoot() + "/post");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filterLoader;
	}
}
