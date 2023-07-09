package com.lft.springbootinit.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
*@Pakage com.lft.springbootinit.model.dto.post
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   创建请求
*/
@Data
public class ChartAddRequest implements Serializable {

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图表数据
     */
    private String chartData;

    /**
     * 图标类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}