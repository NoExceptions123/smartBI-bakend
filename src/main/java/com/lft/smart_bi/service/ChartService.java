package com.lft.smart_bi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lft.smart_bi.model.dto.chart.ChartQueryRequest;
import com.lft.smart_bi.model.entity.Chart;

/**
 * @author 1213
 * @description 针对表【chart(图表信息表)】的数据库操作Service
 * @createDate 2023-07-09
 */
public interface ChartService extends IService<Chart> {

    /**
     * 获取查询条件
     *
     * @param chartQueryRequest
     * @return
     */
    QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest);
}
