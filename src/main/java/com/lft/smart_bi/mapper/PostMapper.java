package com.lft.smart_bi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lft.smart_bi.model.entity.Post;
import java.util.Date;
import java.util.List;

/**
*@Pakage com.lft.smart_bi.mapper
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   帖子数据库操作
*/
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




