package com.example.rpc.version004.server.service;

import com.example.rpc.version004.common.model.service.User;
import com.example.rpc.version004.common.service.UserService;

import java.util.Random;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 10:13
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Integer id) {
        Random random = new Random();
        return new User(id, "jay", random.nextInt(100));
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("insert user success , user = " + user.toString());
        return 1;
    }
}
