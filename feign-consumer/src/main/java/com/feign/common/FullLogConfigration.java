package com.feign.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * Created by NCP-605 on 2017/7/12.
 */
@Configuration
public class FullLogConfigration {
	@Bean
	Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
