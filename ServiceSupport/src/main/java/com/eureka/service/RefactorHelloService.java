package com.eureka.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eureka.model.User;

/**
 * Created by NCP-605 on 2017/7/11.
 */
@RequestMapping("/refactor")
public interface RefactorHelloService {
	@RequestMapping("/sayHello1")
	String sayHello(@RequestParam("name") String name);

	@RequestMapping("/sayHello2")
	String sayHello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@RequestMapping("/sayHello3")
	String sayHello(@RequestBody User user);
}
