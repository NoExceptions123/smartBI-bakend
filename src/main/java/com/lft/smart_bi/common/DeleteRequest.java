package com.lft.smart_bi.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Pakage com.lft.smart_bi.common
 * @auther 邮专第一深情
 * @date 2023/7/8
 * @Description 通用删除请求
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}