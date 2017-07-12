package com.feign.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.feign.model.User;

/**
 * Created by NCP-605 on 2017/7/12.
 */
@Service
public class HelloServiceFalback implements HelloService {
	private Logger logger = LoggerFactory.getLogger(HelloServiceFalback.class);

	public String sayHello() {
		logger.error("sayHello fallback method invoke...");
		return "error";
	}

	@Override
	public String sayHello(@RequestParam("name") String name) {
		return "error";
	}

	@Override
	public String sayHello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return "error";
	}

	@Override
	public String sayHello(@RequestBody User user) {
		return "error";
	}
}
