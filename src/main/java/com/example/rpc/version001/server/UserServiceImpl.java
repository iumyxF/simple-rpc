package com.example.rpc.version001.server;

import com.example.rpc.version001.common.User;
import com.example.rpc.version001.common.UserService;

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
}
