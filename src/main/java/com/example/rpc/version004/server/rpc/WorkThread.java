package com.example.rpc.version004.server.rpc;

import com.example.rpc.version004.common.model.rpc.RpcRequest;
import com.example.rpc.version004.common.model.rpc.RpcResponse;
import com.example.rpc.version004.server.rpc.ServiceRegister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/26 9:14
 */
public class WorkThread implements Runnable {

    private final Socket socket;

    private final ServiceRegister register;

    public WorkThread(Socket socket, ServiceRegister register) {
        this.socket = socket;
        this.register = register;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) ois.readObject();
            RpcResponse response = handler(request);
            oos.writeObject(response);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An exception occurred while processing RPC request");
            e.printStackTrace();
        }
    }

    private RpcResponse handler(RpcRequest request) {
        String className = request.getInterfaceName();
        Object service = register.getService(className);
        Method method;
        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object res = method.invoke(service, request.getParams());
            return RpcResponse.success(res);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("handler error");
            return RpcResponse.fail();
        }
    }
}