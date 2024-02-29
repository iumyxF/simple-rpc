package com.example.rpc.version004.server.rpc.netty;

import com.example.rpc.version004.common.codec.MyDecoder;
import com.example.rpc.version004.common.codec.MyEncoder;
import com.example.rpc.version004.common.serializer.JsonSerializer;
import com.example.rpc.version004.server.rpc.ServiceRegister;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 9:07
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    private final ServiceRegister register;

    public NettyServerInitializer(ServiceRegister register) {
        this.register = register;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyEncoder(new JsonSerializer()));
        pipeline.addLast(new MyDecoder(new JsonSerializer()));
        pipeline.addLast(new NettyServerHandler(register));
    }
}
