package com.eureka.service;

import java.util.List;

import com.eureka.model.User;

/**
 * Created by NCP-605 on 2017/7/10.
 */
public interface UserService {
	User getUserInfo(String id);

	List<User> getUserInfoByIds(String ids);

	String updateUserInfo(User user);

}
