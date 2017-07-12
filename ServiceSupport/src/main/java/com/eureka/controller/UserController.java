package com.eureka.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eureka.model.User;
import com.eureka.service.UserService;

/**
 * Created by NCP-605 on 2017/7/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getUserInfoByIds", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserInfoByIds(String ids) {
		LOGGER.warn("【currentThread = " + Thread.currentThread().getName() + "】");
		return userService.getUserInfoByIds(ids);
	}

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public User getUserInfo(String id) {
		LOGGER.warn("【currentThread = " + Thread.currentThread().getName() + "】");
		return userService.getUserInfo(id);
	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserInfo(User user) {
		return userService.updateUserInfo(user);
	}

}
