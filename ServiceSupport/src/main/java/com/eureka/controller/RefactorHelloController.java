package com.eureka.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.model.User;
import com.eureka.service.RefactorHelloService;

/**
 * Created by NCP-605 on 2017/7/5.
 */
@RestController
public class RefactorHelloController implements RefactorHelloService {

	@Override
	public String sayHello(@RequestParam("name") String name) {

		return "refactor/sayHello1....name=" + name;
	}

	@Override
	public String sayHello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return "refactor/sayHello2....name=" + name + ", age=" + age;
	}

	@Override
	public String sayHello(@RequestBody User user) {
		return "refactor/sayHello3....name=" + user.getName() + ", age=" + user.getAge();
	}

}
