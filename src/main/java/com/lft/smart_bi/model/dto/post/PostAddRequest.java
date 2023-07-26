package com.lft.smart_bi.model.dto.post;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
*@Pakage com.lft.smart_bi.model.dto.post
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   创建请求
*/
@Data
public class PostAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}