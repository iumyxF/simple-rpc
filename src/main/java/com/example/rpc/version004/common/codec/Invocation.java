package com.example.rpc.version004.common.codec;

import com.example.rpc.version004.common.model.rpc.Message;
import com.example.rpc.version004.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 9:34
 */
@Data
@NoArgsConstructor
public class Invocation {

    /**
     * 类型
     */
    private String type;
    /**
     * 消息，JSON 格式
     */
    private String message;

    public Invocation(String type, Message message) {
        this.type = type;
        this.message = JsonUtils.writeValueAsString(message);
    }
}
