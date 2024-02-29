package com.example.rpc.version004.common.service;

import com.example.rpc.version004.common.model.service.Blog;

/**
 * The interface Blog service.
 *
 * @author iumyx
 * @description:
 * @date 2024 /2/26 8:59
 */
public interface BlogService {

    /**
     * Gets by id.
     *
     * @param id the id
     * @return Blog
     */
    Blog getById(Integer id);
}
