package com.example.rpc.version002.client;

import com.example.rpc.version002.common.User;
import com.example.rpc.version002.common.UserService;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/24 11:24
 */
public class RpcClient {

    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8848);
        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getById(123);
        System.out.println("getById user = " + user.toString());

        Integer integer = proxy.insertUser(user);
        System.out.println("insert user result = " + integer);
    }
}
