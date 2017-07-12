package com.eureka.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.model.User;

/**
 * Created by NCP-605 on 2017/7/5.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello(
		@RequestParam(value = "invokeTimes", required = false, defaultValue = "1") Integer invokeTimes) {
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		logger.info("sayHello invoking....");
		logger.info("【 invokeTimes: " + invokeTimes + "】Host: " + serviceInstance.getHost() + ", Port: "
			+ serviceInstance.getPort() + ", ServiceId: " + serviceInstance.getServiceId());
		int random=new Random().nextInt(3000);
		try {
			Thread.sleep(random);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello world...."+random;
	}

	@RequestMapping("/sayHello1")
	public String sayHello(@RequestParam("name") String name) {

		return "sayHello1....name=" + name;
	}

	@RequestMapping("/sayHello2")
	public String sayHello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return "sayHello2....name=" + name + ", age=" + age;
	}

	@RequestMapping("/sayHello3")
	public String sayHello(@RequestBody User user) {
		return "sayHello3....name=" + user.getName() + ", age=" + user.getAge();
	}

}
