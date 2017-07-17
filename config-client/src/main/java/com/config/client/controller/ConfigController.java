package com.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by NCP-605 on 2017/7/13.
 */
@Controller
@RefreshScope
@RequestMapping("/config")
public class ConfigController {
	@Value("${from}")
	private String from;

	@Autowired
	private Environment env;

	@RequestMapping("/from")
	@ResponseBody
	public String from() {
		return from;
	}


	@RequestMapping("/fromEnvironment")
	@ResponseBody
	public String fromEnvironment() {
		return env.getProperty("from", "default");
	}

}
