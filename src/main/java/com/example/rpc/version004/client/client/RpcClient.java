package com.example.rpc.version004.client.client;

import com.example.rpc.version004.common.model.rpc.RpcRequest;
import com.example.rpc.version004.common.model.rpc.RpcResponse;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 8:55
 */
public interface RpcClient {

    RpcResponse sendRequest(RpcRequest request);
}
