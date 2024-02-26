package com.example.rpc.version003.common.model.rpc;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 15:04
 */
@Data
@Builder
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object data;

    public static RpcResponse success(Object data) {
        return RpcResponse.builder().code(200).data(data).build();
    }
    public static RpcResponse fail() {
        return RpcResponse.builder().code(500).message("server error").build();
    }
}
