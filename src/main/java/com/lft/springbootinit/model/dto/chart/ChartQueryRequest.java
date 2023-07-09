package com.lft.springbootinit.model.dto.chart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lft.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
*@Pakage com.lft.springbootinit.model.dto.post
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   查询请求
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class ChartQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图标类型
     */
    private String chartType;

    /**
     * 用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}