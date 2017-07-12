package com.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by NCP-605 on 2017/7/12.
 */
@Controller
@RequestMapping("/local")
public class HelloController {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "local hello...";
	}
}
