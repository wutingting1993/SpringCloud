package com.customer.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.controller.ConsumeController;
import com.customer.model.User;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * Created by NCP-605 on 2017/7/5.
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {

	@Autowired
	private RestTemplate restTemplate;

	public ConsumeServiceImpl() {
		super();
	}

	@CacheResult
	@HystrixCommand(fallbackMethod = "getUserInfoError", commandKey = "getUserInfo", ignoreExceptions = {
		HystrixBadRequestException.class})
	@Override
	public User getUserInfo(@CacheKey String id) {
		logger.warn("【id = " + id + "】 invoke getUserInfo method..."+Thread.currentThread().getName());
		logger.warn("【currentThread = " + Thread.currentThread().getName() + "】");
		return restTemplate.getForEntity("http://SERVICE-SUPPORT/user/getUserInfo?id=" + id, User.class).getBody();
	}

	@HystrixCollapser(batchMethod = "getUserInfoByIds", collapserProperties = {
		@HystrixProperty(name = "timerDelayInMilliseconds", value = "5000"),
		@HystrixProperty(name = "maxRequestsInBatch", value = "5")
	}, scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL)
	@Override
	public Future<Object> getUserInfoWithHystrixCollapser(String id) {
		throw new RuntimeException("This method body should not be executed");
	}

	@HystrixCommand(fallbackMethod = "getUserInfoByIdsError", commandKey = "getUserInfoByIds", ignoreExceptions = {
		HystrixBadRequestException.class})
	@Override
	public List<Object> getUserInfoByIds(List<String> ids) {
		logger.warn("【ids = " + ids + "】 invoke getUserInfoByIds method...");
		return restTemplate.getForEntity(
			"http://SERVICE-SUPPORT/user/getUserInfoByIds?ids=" + StringUtils.join(ids, ","),
			List.class).getBody();
	}

	@CacheRemove(commandKey = "getUserInfo")
	@HystrixCommand(fallbackMethod = "updateUserInfoError", commandKey = "updateUserInfo")
	@Override
	public String updateUserInfo(@CacheKey("id") User user) {
		logger.warn("invoke updateUserInfo method...");
		return restTemplate.postForEntity("http://SERVICE-SUPPORT/user/updateUserInfo", user, String.class).getBody();
	}

	private final Logger logger = LoggerFactory.getLogger(ConsumeController.class);
	private static int invokeTimes = 0;

	@HystrixCommand(fallbackMethod = "error", commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
	})
	@Override
	public String customerSayHello() {
		//HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000);
		logger.warn("customerSayHello invoking ...");
		invokeTimes++;
		int millis = new Random().nextInt(3000);
		logger.warn("sleep " + millis + " millis ....");
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "【 invokeTimes: " + invokeTimes + "】" + restTemplate.getForEntity(
			"http://SERVICE-SUPPORT/hello/sayHello?invokeTimes=" + invokeTimes, String.class).getBody();
	}

	private String error(Throwable throwable) {
		logger.error("error message：" + throwable.getMessage());
		return "error";
	}

	private User getUserInfoError(String id, Throwable throwable) {
		logger.error("error message：" + throwable.getMessage());
		return new User();
	}

	private List<Object> getUserInfoByIdsError(List<String> ids, Throwable throwable) {
		logger.error("error message：" + throwable.getMessage());
		return Collections.emptyList();
	}

	private String updateUserInfoError(User user, Throwable throwable) {
		logger.error("error message：" + throwable.getMessage());
		return "error";
	}
}
