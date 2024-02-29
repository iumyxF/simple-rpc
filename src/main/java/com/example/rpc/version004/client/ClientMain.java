package com.example.rpc.version004.client;

import com.example.rpc.version004.client.client.NettyRpcClient;
import com.example.rpc.version004.client.client.SimpleRpcClient;
import com.example.rpc.version004.common.model.service.Blog;
import com.example.rpc.version004.common.model.service.User;
import com.example.rpc.version004.common.service.BlogService;
import com.example.rpc.version004.common.service.UserService;
import com.example.rpc.version004.common.utils.JsonUtils;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/24 11:24
 */
public class ClientMain {

    public static void main(String[] args) {
        NettyRpcClient nettyRpcClient = new NettyRpcClient("127.0.0.1", 8848);
        ClientProxy clientProxy = new ClientProxy(nettyRpcClient);
        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getById(123);
        System.out.println("getById user = " + user.toString());

        Integer integer = proxy.insertUser(new User(123,"jay",123));
        System.out.println("insert user result = " + integer);

        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blog = blogService.getById(10086);
        System.out.println("getById blog = " + blog.toString());
    }
}
