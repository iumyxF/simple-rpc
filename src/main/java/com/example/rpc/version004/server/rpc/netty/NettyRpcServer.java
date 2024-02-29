package com.example.rpc.version004.server.rpc.netty;

import com.example.rpc.version004.server.rpc.RpcServer;
import com.example.rpc.version004.server.rpc.ServiceRegister;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 8:53
 */
public class NettyRpcServer implements RpcServer {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Channel channel;
    
    private final ServiceRegister register;

    public NettyRpcServer(ServiceRegister register) {
        this.register = register;
    }

    @Override
    public void start(int port) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new NettyServerInitializer(register));
        ChannelFuture future;
        try {
            future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                channel = future.channel();
                System.out.println("netty server 启动成功! 端口为 " + port);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
