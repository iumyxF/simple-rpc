package com.example.rpc.version003.server.rpc;

import com.example.rpc.version003.server.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author iumyx
 * @description: rpc server 普通实现
 * @date 2024/2/26 9:08
 */
public class MyRpcServer implements RpcServer {

    private final ServiceRegister register;

    public MyRpcServer(ServiceRegister register) {
        this.register = register;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server start listen port in " + port + " ...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, register)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server start error ...");
        }
    }

    @Override
    public void stop() {

    }
}
