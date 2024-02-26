package com.example.rpc.version003.client;

import com.example.rpc.version003.common.model.rpc.RpcRequest;
import com.example.rpc.version003.common.model.rpc.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/24 11:19
 */
public class ClientProxy implements InvocationHandler {

    private final String host;

    private final int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        RpcResponse rpcResponse = IoClient.sendRequest(host, port, rpcRequest);
        if (null == rpcResponse){
            return null;
        }
        return rpcResponse.getData();
    }

    @SuppressWarnings("unchecked")
    <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                this);
    }
}
