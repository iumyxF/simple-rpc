package com.example.rpc.version004.common.model.rpc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 15:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RpcRequest implements Message, Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TYPE = "RPC_REQUEST";

    /**
     * 调用接口名
     */
    private String interfaceName;

    /**
     * 调用方法
     */
    private String methodName;

    /**
     * 参数列表
     */
    private Object[] params;

    /**
     * 参数类型列表
     */
    private Class<?>[] paramsTypes;
}