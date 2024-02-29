package com.example.rpc.version004.server.rpc;

/**
 * The interface Rpc server.
 *
 * @author iumyx
 * @description:
 * @date 2024 /2/26 9:07
 */
public interface RpcServer {

    /**
     * Start.
     *
     * @param port the port
     */
    void start(int port);

    /**
     * Stop.
     */
    void stop();
}
