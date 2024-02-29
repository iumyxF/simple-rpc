package com.example.rpc.version004.common.serializer;

import com.example.rpc.version004.common.utils.JsonUtils;

import java.io.IOException;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/27 9:22
 */
public class JsonSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        return JsonUtils.getInstance().writeValueAsBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        return JsonUtils.getInstance().readValue(bytes,type);
    }
}
