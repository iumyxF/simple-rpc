package com.example.rpc.version003.client;

import com.example.rpc.version003.common.model.rpc.RpcRequest;
import com.example.rpc.version003.common.model.rpc.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author iumyx
 * @description: 通信
 * @date 2024/2/23 15:13
 */
public class IoClient {

    /**
     * 封装一次调用请求
     *
     * @param host    服务器ip
     * @param port    服务器端口
     * @param request 请求对象
     * @return 响应结果
     */
    public static RpcResponse sendRequest(String host, int port, RpcRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            RpcResponse response = (RpcResponse) objectInputStream.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
