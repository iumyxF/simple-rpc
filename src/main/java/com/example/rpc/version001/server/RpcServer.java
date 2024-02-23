package com.example.rpc.version001.server;

import com.example.rpc.version001.common.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 10:16
 */
public class RpcServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8848);
            System.out.println("server start listen port in 8848 ...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        //读取数据
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        int userId = ois.readInt();
                        //执行业务处理
                        User user = userService.getById(userId);
                        //返回结果
                        oos.writeObject(user);
                        oos.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
