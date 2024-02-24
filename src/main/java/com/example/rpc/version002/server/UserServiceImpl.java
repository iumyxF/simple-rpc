package com.example.rpc.version002.server;


import com.example.rpc.version002.common.User;
import com.example.rpc.version002.common.UserService;

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
