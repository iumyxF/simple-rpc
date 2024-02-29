package com.example.rpc.version004.client.client;

import com.example.rpc.version004.common.model.rpc.RpcRequest;
import com.example.rpc.version004.common.model.rpc.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author iumyx
 * @description: 通信
 * @date 2024/2/23 15:13
 */
public class SimpleRpcClient implements RpcClient{

    private String host;

    private int port;

    public SimpleRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 封装一次调用请求
     *
     * @param request 请求对象
     * @return 响应结果
     */
    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            return (RpcResponse) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
