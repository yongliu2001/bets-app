package com.yong.service;

import com.yong.domain.User;
import com.yong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yongliu on 25/6/18.
 */
@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
