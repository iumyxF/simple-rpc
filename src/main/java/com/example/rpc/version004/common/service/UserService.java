package com.example.rpc.version004.common.service;

import com.example.rpc.version004.common.model.service.User;

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

    /**
     * Insert user integer.
     *
     * @param user the user
     * @return the integer
     */
    Integer insertUser(User user);
}
