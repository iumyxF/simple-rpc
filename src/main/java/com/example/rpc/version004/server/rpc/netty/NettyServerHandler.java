package com.example.rpc.version004.server.rpc.netty;

import com.example.rpc.version004.common.codec.Invocation;
import com.example.rpc.version004.common.model.rpc.RpcRequest;
import com.example.rpc.version004.common.model.rpc.RpcResponse;
import com.example.rpc.version004.common.utils.JsonUtils;
import com.example.rpc.version004.server.rpc.ServiceRegister;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 9:48
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<Invocation> {

    private final ServiceRegister register;

    public NettyServerHandler(ServiceRegister register) {
        this.register = register;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        String type = invocation.getType();
        // 这里暂时只有两种type，如果多种可以使用map进行匹配
        if (RpcRequest.TYPE.equals(type)) {
            RpcRequest request = JsonUtils.readValue(invocation.getMessage(), RpcRequest.class);
            if (null == request) {
                System.out.println("接收到的消息为空");
                return;
            }
            RpcResponse response = handler(request);
            if (null == response) {
                ctx.writeAndFlush(new Invocation(RpcResponse.TYPE, RpcResponse.fail()));
            }
            System.out.println("server - 接受请求: " + request);
            System.out.println("server - 响应请求: " + response);
            ctx.writeAndFlush(new Invocation(RpcResponse.TYPE, response));
        } else if (RpcResponse.TYPE.equals(type)) {
            System.out.println("服务端暂时不支持处理RpcResponse消息");
        }
    }

    private RpcResponse handler(RpcRequest request) {
        String className = request.getInterfaceName();
        Object service = register.getService(className);
        if (null == service) {
            return null;
        }
        Method method;
        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            //经过序列化后，实体类型会变成linkedHashMap，需要进行转化处理
            Object[] params = new Object[request.getParams().length];
            for (int i = 0; i < params.length; i++) {
                Object p = request.getParams()[i];
                Class<?> t = request.getParamsTypes()[i];
                params[i] = JsonUtils.getInstance().convertValue(p, t);
            }
            Object res = method.invoke(service, params);
            return RpcResponse.success(res);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("handler error");
            return RpcResponse.fail();
        }
    }
}
