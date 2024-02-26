package com.example.rpc.version003.server;

import com.example.rpc.version003.server.rpc.MyRpcServer2;
import com.example.rpc.version003.server.rpc.ServiceRegister;
import com.example.rpc.version003.server.service.BlogServiceImpl;
import com.example.rpc.version003.server.service.UserServiceImpl;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 10:16
 */
public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        ServiceRegister register = new ServiceRegister();
        register.register(userService);
        register.register(blogService);

        //new MyRpcServer(register).start(8848);
        new MyRpcServer2(register).start(8848);
    }
}
