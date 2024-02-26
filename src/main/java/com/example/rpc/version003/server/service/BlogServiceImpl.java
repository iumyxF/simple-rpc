package com.example.rpc.version003.server.service;

import com.example.rpc.version003.common.model.service.Blog;
import com.example.rpc.version003.common.service.BlogService;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/26 9:02
 */
public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getById(Integer id) {
        return new Blog(id, 9999, "The Red and The Black");
    }
}