package com.customer.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.User;
import com.customer.service.ConsumeService;
import com.customer.service.UserCollapseCommand;

/**
 * Created by NCP-605 on 2017/7/5.
 */
@RestController
@RequestMapping("/customer")
public class ConsumeController {
	@Autowired
	private ConsumeService consumeService;



	@RequestMapping(value = "/customerSayHello", method = RequestMethod.GET)
	@ResponseBody
	public String customerSayHello() {
		return consumeService.customerSayHello();
	}

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public User getUserInfo(String id) {
		User userInfo = consumeService.getUserInfo(id);
		System.out.println("第一次：" + userInfo.getId());
		User userInfo2 = consumeService.getUserInfo(id);
		System.out.println("第二次：" + userInfo2.getId());
		consumeService.updateUserInfo(userInfo2);
		User userInfo3 = consumeService.getUserInfo(id);
		System.out.println("第三次：" + userInfo3.getId());
		User userInfo4 = consumeService.getUserInfo(id);
		System.out.println("第四次：" + userInfo4.getId());
		return userInfo;
	}

	@RequestMapping(value = "/getUserInfoWithHystrixCollapser", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInfoWithHystrixCollapser(String id) throws ExecutionException, InterruptedException {
		return consumeService.getUserInfoWithHystrixCollapser(id).get();
	}


	@RequestMapping(value = "/getUserInfoWithUserCollapseCommand", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInfoWithUserCollapseCommand(String id) throws ExecutionException, InterruptedException {
		return new UserCollapseCommand(id, consumeService).queue().get();
	}
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserInfo(User user) {
		return consumeService.updateUserInfo(user);
	}
}
