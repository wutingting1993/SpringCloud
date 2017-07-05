package com.customer.service;

import com.customer.controller.ConsumeController;
import com.customer.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * Created by NCP-605 on 2017/7/5.
 */
@Service

public class ConsumeServiceImpl implements ConsumeService {

    public ConsumeServiceImpl() {
        super();
    }


    @CacheResult
    @HystrixCommand(fallbackMethod = "getUserInfoError", commandKey = "getUserInfo")
    @Override
    public User getUserInfo(@CacheKey String id) {
        logger.warn("【id = " + id + "】 invoke getUserInfo method...");
        return restTemplate.getForEntity("http://SERVICE-SUPPORT/hello/getUserInfo?id=" + id, User.class).getBody();
    }

    @CacheRemove(commandKey = "getUserInfo")
    @HystrixCommand(fallbackMethod = "error", commandKey = "updateUserInfo")
    @Override
    public String updateUserInfo(User user) {
        logger.warn("invoke updateUserInfo method...");
        return restTemplate.postForEntity("http://SERVICE-SUPPORT/hello/updateUserInfo", user, String.class).getBody();
    }

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(ConsumeController.class);
    private static int invokeTimes = 0;

    @HystrixCommand(fallbackMethod = "error")
    @Override
    public String customerSayHello() {
        logger.warn("customerSayHello invoking ...");
        invokeTimes++;
        int millis = new Random().nextInt(3000);
        logger.warn("sleep " + millis + " millis ....");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "【 invokeTimes: " + invokeTimes + "】" + restTemplate.getForEntity("http://SERVICE-SUPPORT/hello/sayHello?invokeTimes=" + invokeTimes, String.class).getBody();
    }

    private String error(Throwable throwable) {
        logger.error("error message：" + throwable.getMessage());
        return "error";
    }

    private User getUserInfoError(String id, Throwable throwable) {
        logger.error("error message：" + throwable.getMessage());
        return new User();
    }
}
