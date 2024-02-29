package com.example.rpc.version004.client.client;

import com.example.rpc.version004.common.codec.MyDecoder;
import com.example.rpc.version004.common.codec.MyEncoder;
import com.example.rpc.version004.common.serializer.JsonSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/29 10:45
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyEncoder(new JsonSerializer()));
        pipeline.addLast(new MyDecoder(new JsonSerializer()));
        pipeline.addLast(new NettyClientHandler());
    }
}
