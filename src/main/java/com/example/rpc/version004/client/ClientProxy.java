package com.example.rpc.version004.client;

import com.example.rpc.version004.client.client.RpcClient;
import com.example.rpc.version004.common.model.rpc.RpcRequest;
import com.example.rpc.version004.common.model.rpc.RpcResponse;
import com.example.rpc.version004.common.utils.JsonUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/24 11:19
 */
public class ClientProxy implements InvocationHandler {

    private RpcClient rpcClient;

    public ClientProxy(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        System.out.println("client 封装的 request = " + rpcRequest);
        RpcResponse rpcResponse = rpcClient.sendRequest(rpcRequest);
        if (null == rpcResponse) {
            return null;
        }
        return JsonUtils.readValue(JsonUtils.writeValueAsString(rpcResponse.getData()), method.getReturnType());
    }

    @SuppressWarnings("unchecked")
    <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                this);
    }
}
