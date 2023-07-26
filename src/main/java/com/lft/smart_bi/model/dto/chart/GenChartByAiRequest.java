package com.lft.smart_bi.model.dto.chart;

import lombok.Data;

/**
 * @Pakage com.lft.smart_bi.model.dto.chart
 * @auther 邮专第一深情
 * @date 2023/07/12 14:53
 */
@Data
public class GenChartByAiRequest {

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 数据表名
     */
    private String name;

    /**
     * 图标类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;

}
