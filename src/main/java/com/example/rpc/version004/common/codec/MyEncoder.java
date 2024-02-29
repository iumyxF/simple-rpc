package com.example.rpc.version004.common.codec;

import com.example.rpc.version004.common.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 9:18
 */
public class MyEncoder extends MessageToByteEncoder<Invocation> {

    private Serializer serializer;

    public MyEncoder(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Invocation msg, ByteBuf out) throws IOException {
        byte[] bytes = serializer.serialize(msg);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}