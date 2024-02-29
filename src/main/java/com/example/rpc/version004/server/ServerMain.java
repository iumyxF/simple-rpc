package com.example.rpc.version004.server;

import com.example.rpc.version004.server.rpc.ServiceRegister;
import com.example.rpc.version004.server.rpc.netty.NettyRpcServer;
import com.example.rpc.version004.server.service.BlogServiceImpl;
import com.example.rpc.version004.server.service.UserServiceImpl;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 10:16
 */
public class ServerMain {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();

        ServiceRegister register = new ServiceRegister();
        register.register(userService);
        register.register(blogService);

        new NettyRpcServer(register).start(8848);
    }
}
