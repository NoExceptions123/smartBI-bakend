package com.lft.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lft.springbootinit.model.entity.Chart;
import com.lft.springbootinit.service.ChartService;
import com.lft.springbootinit.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
* @author 1213
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
* @createDate 2023-07-09 10:38:45
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

}




