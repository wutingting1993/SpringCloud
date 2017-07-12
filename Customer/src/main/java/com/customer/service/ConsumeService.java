package com.customer.service;

import java.util.List;
import java.util.concurrent.Future;

import com.customer.model.User;

/**
 * Created by NCP-605 on 2017/7/5.
 */
public interface ConsumeService {
	String customerSayHello();

	User getUserInfo(String id);

	Future<Object> getUserInfoWithHystrixCollapser(String id);

	List<Object> getUserInfoByIds(List<String> ids);

	String updateUserInfo(User user);
}
