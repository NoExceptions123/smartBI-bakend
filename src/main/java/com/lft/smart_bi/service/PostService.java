package com.lft.smart_bi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lft.smart_bi.model.dto.post.PostQueryRequest;
import com.lft.smart_bi.model.entity.Post;

/**
 *@Pakage com.lft.smart_bi.service
 *@auther 邮专第一深情
 *@date 2023/7/8
 *@Description   帖子服务
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);


}