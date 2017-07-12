package com.eureka.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.eureka.model.User;

/**
 * Created by NCP-605 on 2017/7/10.
 */
@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public User getUserInfo(String id) {
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		logger.info("getUserInfo invoking....");
		logger.info("【 userid: " + id + "】Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort()
			+ ", ServiceId: " + serviceInstance.getServiceId());
		User user = new User();
		user.setId(id);
		user.setName("name" + id);
		user.setAge(Integer.parseInt(id));
		return user;
	}

	@Override
	public List<User> getUserInfoByIds(String ids) {
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		logger.info("getUserInfoByIds invoking....");
		logger.info("【 userids: " + ids + "】Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort()
			+ ", ServiceId: " + serviceInstance.getServiceId());
		if (StringUtils.isBlank(ids)) {
			return null;
		}
		String[] idList = ids.split(",");
		List<User> list = new ArrayList<>(idList.length);
		for (String id : idList) {
			list.add(getUserInfo(id));
		}
		return list;
	}

	@Override
	public String updateUserInfo(User user) {
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		logger.info("updateUserInfo invoking....");
		logger.info(
			"【 Name: " + user.getName() + "】Host: " + serviceInstance.getHost() + ", Port: " + serviceInstance.getPort()
				+ ", ServiceId: " + serviceInstance.getServiceId());
		return "success";
	}
}
