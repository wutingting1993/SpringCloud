package com.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feign.common.FullLogConfigration;
import com.feign.model.User;

/**
 * Created by NCP-605 on 2017/7/11.
 */
//@FeignClient(value = "SERVICE-SUPPORT", configuration = DisableHystrixConfigration.class)
@FeignClient(value = "SERVICE-SUPPORT",
	configuration = FullLogConfigration.class,
	fallback = HelloServiceFalback.class)
public interface HelloService {
	@RequestMapping("/hello/sayHello")
	String sayHello();

	@RequestMapping("/hello/sayHello1")
	String sayHello(@RequestParam("name") String name);

	@RequestMapping("/hello/sayHello2")
	String sayHello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@RequestMapping("/hello/sayHello3")
	String sayHello(@RequestBody User user);
}
