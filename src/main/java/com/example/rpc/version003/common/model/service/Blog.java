package com.example.rpc.version003.common.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/26 8:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer useId;

    private String title;
}
