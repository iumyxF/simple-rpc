package com.example.rpc.version001.common;

/**
 * The interface User service.
 *
 * @author iumyx
 * @description:
 * @date 2024 /2/23 10:12
 */
public interface UserService {

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    User getById(Integer id);
}
