package com.lft.smart_bi.model.dto.chart;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
*@Pakage com.lft.smart_bi.model.dto.post
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   更新请求
*/
@Data
public class ChartUpdateRequest implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 图表数据
     */
    private String chartData;

    /**
     * 图标类型
     */
    private String chartType;



    private static final long serialVersionUID = 1L;
}