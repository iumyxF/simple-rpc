package com.example.rpc.version003.server.rpc;

import com.example.rpc.version003.server.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author iumyx
 * @description: 线程池版rpc server
 * @date 2024/2/26 9:35
 */
public class MyRpcServer2 implements RpcServer {

    private final ThreadPoolExecutor threadPool;

    private final ServiceRegister register;

    public MyRpcServer2(ServiceRegister register) {
        this.threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        this.register = register;
    }

    public MyRpcServer2(ServiceRegister register,
                        int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue) {
        this.register = register;
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void start(int port) {
        System.out.println("thread pool version rpc server start ...");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new WorkThread(socket, register));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
