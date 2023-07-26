package com.lft.smart_bi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lft.smart_bi.constant.CommonConstant;
import com.lft.smart_bi.mapper.ChartMapper;
import com.lft.smart_bi.model.dto.chart.ChartQueryRequest;
import com.lft.smart_bi.model.entity.Chart;
import com.lft.smart_bi.service.ChartService;
import com.lft.smart_bi.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * @author 1213
 * @description 针对表【chart(图表信息表)】的数据库操作Service实现
 * @createDate 2023-07-09 10:38:45
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
        implements ChartService {
    /**
     * 获取查询包装类
     *
     * @param chartQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest) {

        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        if (chartQueryRequest == null) {
            return queryWrapper;
        }
        Long id = chartQueryRequest.getId();
        String name = chartQueryRequest.getName();
        String goal = chartQueryRequest.getGoal();
        String chartType = chartQueryRequest.getChartType();
        Long userId = chartQueryRequest.getUserId();
        String sortField = chartQueryRequest.getSortField();
        String sortOrder = chartQueryRequest.getSortOrder();
        queryWrapper.ne(id != null && id > 0, "id", id);
        queryWrapper.like(ObjectUtils.isNotEmpty(name), "name",name);
        queryWrapper.eq(ObjectUtils.isNotEmpty(goal), "goal", goal);
        queryWrapper.eq(ObjectUtils.isNotEmpty(chartType), "chartType", chartType);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

}




