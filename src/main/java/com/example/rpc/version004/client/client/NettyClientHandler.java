package com.example.rpc.version004.client.client;

import com.example.rpc.version004.common.codec.Invocation;
import com.example.rpc.version004.common.model.rpc.RpcResponse;
import com.example.rpc.version004.common.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/29 10:45
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<Invocation> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        AttributeKey<RpcResponse> key = AttributeKey.valueOf("RpcResponse");
        ctx.channel().attr(key).set(JsonUtils.readValue(invocation.getMessage(), RpcResponse.class));
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
