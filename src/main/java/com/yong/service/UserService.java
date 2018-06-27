package com.yong.service;

import com.yong.domain.User;

/**
 * Created by yongliu on 25/6/18.
 */
public interface UserService {

    User findUserByName(String name);

    User saveUser(User user);
}
