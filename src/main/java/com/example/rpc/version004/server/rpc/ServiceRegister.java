package com.example.rpc.version004.server.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iumyx
 * @description: 注册中心
 * @date 2024/2/26 9:41
 */
public class ServiceRegister {

    private final Map<String, Object> register;

    public ServiceRegister() {
        this.register = new HashMap<>(16);
    }

    public void register(Object service) {
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> aClass : interfaces) {
            register.put(aClass.getName(), service);
        }
        System.out.println(register);
    }

    public Object getService(String interfaceName) {
        return register.get(interfaceName);
    }
}
