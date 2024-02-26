package com.example.rpc.version003.common.model.rpc;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 15:03
 */
@Data
@Builder
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

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