package com.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feign.model.User;
import com.feign.service.HelloService;
import com.feign.service.RefactorHelloService;

/**
 * Created by NCP-605 on 2017/7/11.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private HelloService helloService;

	@Autowired
	private RefactorHelloService refactorHelloService;

	@RequestMapping(value = "/testHelloServiceFalback", method = RequestMethod.GET)
	@ResponseBody
	public String testHelloServiceFalback() {
		String s = helloService.sayHello();
		System.out.println("fegin-consumer->testHelloServiceFalback->out: " + s);
		return s;
	}
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		String s = helloService.sayHello();
		System.out.println("fegin-consumer->sayHello->out: " + s);
		return s;
	}

	@RequestMapping(value = "/userSayHello", method = RequestMethod.GET)
	@ResponseBody
	public String userSayHello() {
		StringBuilder builder = new StringBuilder();
		User user = new User();
		user.setAge(20000);
		user.setName("newusername");
		builder.append(helloService.sayHello("name1"))
			.append("\n")
			.append(helloService.sayHello("haha", 20))
			.append("\n")
			.append(helloService.sayHello(user))
			.append("\n");
		System.out.println("fegin-consumer->userSayHello->out: " + builder.toString());
		return builder.toString();
	}

	@RequestMapping(value = "/userSayHello2", method = RequestMethod.GET)
	@ResponseBody
	public String userSayHello2() {
		StringBuilder builder = new StringBuilder();
//		User user = new User();
//		user.setAge(20000);
//		user.setName("newusername");
//		builder.append(refactorHelloService.sayHello("name1"))
//			.append("\n")
//			.append(refactorHelloService.sayHello("haha", 20))
//			.append("\n")
//			.append(refactorHelloService.sayHello(user))
//			.append("\n");
//		System.out.println("fegin-consumer->userSayHello2->out: " + builder.toString());
		return builder.toString();
	}
}
