package com.eureka.controller;

import com.eureka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by NCP-605 on 2017/7/5.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;

    private static int nameid = 0;
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(Integer invokeTimes) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("sayHello invoking....");
        logger.info("【 invokeTimes: " + invokeTimes + "】Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort() + ", ServiceId: " + serviceInstance.getServiceId());
        return "hello world....";
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(String id) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("getUserInfo invoking....");
        logger.info("【 userid: " + id + "】Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort() + ", ServiceId: " + serviceInstance.getServiceId());
        User user = new User();
        user.setId(id);
        user.setName("name" + nameid);
        user.setAge(nameid);
        return user;
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserInfo(User user) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("updateUserInfo invoking....");
        logger.info("【 Name: " + user.getName() + "】Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort() + ", ServiceId: " + serviceInstance.getServiceId());
        return "success";
    }
}
