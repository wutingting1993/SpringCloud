package com.customer.service;

import com.customer.model.User;

/**
 * Created by NCP-605 on 2017/7/5.
 */
public interface ConsumeService {
    String customerSayHello();
    User getUserInfo(String id);
    String updateUserInfo(User user);
}
