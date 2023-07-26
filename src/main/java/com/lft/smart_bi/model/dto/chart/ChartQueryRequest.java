package com.lft.smart_bi.model.dto.chart;

import com.lft.smart_bi.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
*@Pakage com.lft.smart_bi.model.dto.post
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
     * 数据表名
     */
    private String name;

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