package com.lft.smart_bi.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lft.smart_bi.annotation.AuthCheck;
import com.lft.smart_bi.common.BaseResponse;
import com.lft.smart_bi.common.DeleteRequest;
import com.lft.smart_bi.common.ErrorCode;
import com.lft.smart_bi.common.ResultUtils;
import com.lft.smart_bi.constant.RabbitMqConstant;
import com.lft.smart_bi.constant.UserConstant;
import com.lft.smart_bi.exception.BusinessException;
import com.lft.smart_bi.exception.ThrowUtils;
import com.lft.smart_bi.manager.AiManager;
//import com.lft.smart_bi.manager.RedisLimiterManager;
import com.lft.smart_bi.manager.RedisLimiterManager;
import com.lft.smart_bi.model.dto.chart.*;
import com.lft.smart_bi.model.entity.Chart;
import com.lft.smart_bi.model.entity.User;
import com.lft.smart_bi.model.vo.BiResponse;
import com.lft.smart_bi.service.ChartService;
import com.lft.smart_bi.service.UserService;
import com.lft.smart_bi.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Pakage com.lft.smart_bi.controller
 * @auther 邮专第一深情
 * @date 2023/7/8
 * @Description 帖子接口
 */
@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {

    @Resource
    private ChartService chartService;

    @Resource
    private UserService userService;

    @Resource
    private AiManager aiManager;

    @Resource
    private RedisLimiterManager redisLimiterManager;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 创建图标信息
     *
     * @param chartAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addChart(@RequestBody ChartAddRequest chartAddRequest, HttpServletRequest request) {
        if (chartAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartAddRequest, chart);
        User loginUser = userService.getLoginUser(request);
        chart.setUserId(loginUser.getId());
        boolean result = chartService.save(chart);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newChartId = chart.getId();
        return ResultUtils.success(newChartId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteChart(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldChart.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = chartService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param chartUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateChart(@RequestBody ChartUpdateRequest chartUpdateRequest) {
        if (chartUpdateRequest == null || chartUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartUpdateRequest, chart);
        long id = chartUpdateRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = chartService.updateById(chart);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<Chart> getChartVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = chartService.getById(id);
        if (chart == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(chart);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param chartQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Chart>> listChartByPage(@RequestBody ChartQueryRequest chartQueryRequest) {
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Chart> chartPage = chartService.page(new Page<>(current, size),
                chartService.getQueryWrapper(chartQueryRequest));
        return ResultUtils.success(chartPage);
    }

    /**
     * 编辑（用户）
     *
     * @param chartEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editChart(@RequestBody ChartEditRequest chartEditRequest, HttpServletRequest request) {
        if (chartEditRequest == null || chartEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartEditRequest, chart);
        User loginUser = userService.getLoginUser(request);
        long id = chartEditRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldChart.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = chartService.updateById(chart);
        return ResultUtils.success(result);
    }


    /**
     * 智能分析 同步
     *
     * @param multipartFile
     * @param genChartByAiRequest
     * @param request
     * @return
     */
    @PostMapping("/gen")
    public BaseResponse<BiResponse> genChartByAi(@RequestPart("file") MultipartFile multipartFile,
                                                 GenChartByAiRequest genChartByAiRequest, HttpServletRequest request) {
        String goal = genChartByAiRequest.getGoal();
        String name = genChartByAiRequest.getName();
        String chartType = genChartByAiRequest.getChartType();

        ThrowUtils.throwIf(StringUtils.isBlank(goal), ErrorCode.NOT_FOUND_ERROR, "目标为空");
        ThrowUtils.throwIf(StringUtils.isBlank(name) || name.length() > 100, ErrorCode.PARAMS_ERROR);
        /**
         * 防止用户上传非法文件
         */
        long size = multipartFile.getSize();
        String originalFilename = multipartFile.getOriginalFilename();
        final long ONE_MB = 1024 * 1024L;
        ThrowUtils.throwIf(size > ONE_MB, ErrorCode.PARAMS_ERROR, "文件超过1MB");
        String suffix = FileUtil.getSuffix(originalFilename);
        List<String> validFileSuffixList = Arrays.asList("xlsx", "xls");
        ThrowUtils.throwIf(!validFileSuffixList.contains(suffix), ErrorCode.PARAMS_ERROR, "文件格式错误");

        /**
         * 根据用户id限流
         */
        User loginUser = userService.getLoginUser(request);
         redisLimiterManager.doRateLimiter(String.valueOf(loginUser.getId()));

        long bimoudleId = 1659171950288818178L;
        StringBuilder userInput = new StringBuilder();
        userInput.append("分析需求：").append("\n");
        String userGoal = goal;
        if (StringUtils.isNotBlank(chartType)) {
            userGoal += ", 请使用" + chartType;
        }
        userInput.append(userGoal).append("\n");
        userInput.append("原始数据：").append("\n");
        String chartData = ExcelUtils.excelToCsv(multipartFile);
        userInput.append(chartData).append("\n");
        String result = aiManager.doChart(bimoudleId, userInput.toString());
        String[] split = result.split("【【【【【");
        if (split.length < 3) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI生成错误");
        }
        String genChart = split[1].trim();
        String genRsult = split[2].trim();
        Chart chart = new Chart();
        chart.setName(name);
        chart.setGoal(goal);
        chart.setChartData(chartData);
        chart.setChartType(chartType);
        chart.setGenChart(genChart);
        chart.setGenResult(genRsult);
        chart.setUserId(loginUser.getId());
        boolean saveResult = chartService.save(chart);
        ThrowUtils.throwIf(!saveResult, ErrorCode.SYSTEM_ERROR, "图表保存失败");
        BiResponse biResponse = new BiResponse();
        biResponse.setGenChart(genChart);
        biResponse.setGenResult(genRsult);
        biResponse.setChartId(chart.getId());
        return ResultUtils.success(biResponse);
    }

    /**
     * 智能分析异步
     *
     * @param multipartFile
     * @param genChartByAiRequest
     * @param request
     * @return
     */
    @PostMapping("/gen/async")
    public BaseResponse<BiResponse> genChartByAiAsync(@RequestPart("file") MultipartFile multipartFile,
                                                      GenChartByAiRequest genChartByAiRequest, HttpServletRequest request) {
        String goal = genChartByAiRequest.getGoal();
        String name = genChartByAiRequest.getName();
        String chartType = genChartByAiRequest.getChartType();

        ThrowUtils.throwIf(StringUtils.isBlank(goal), ErrorCode.NOT_FOUND_ERROR, "目标为空");
        ThrowUtils.throwIf(StringUtils.isBlank(name) || name.length() > 100, ErrorCode.PARAMS_ERROR);
        /**
         * 防止用户上传非法文件
         */
        long size = multipartFile.getSize();
        String originalFilename = multipartFile.getOriginalFilename();
        final long ONE_MB = 1024 * 1024L;
        ThrowUtils.throwIf(size > ONE_MB, ErrorCode.PARAMS_ERROR, "文件超过1MB");
        String suffix = FileUtil.getSuffix(originalFilename);
        List<String> validFileSuffixList = Arrays.asList("xlsx", "xls");
        ThrowUtils.throwIf(!validFileSuffixList.contains(suffix), ErrorCode.PARAMS_ERROR, "文件格式错误");

        /**
         * 根据用户id限流
         */
        User loginUser = userService.getLoginUser(request);
        redisLimiterManager.doRateLimiter(String.valueOf(loginUser.getId()));

        ;
        StringBuilder userInput = new StringBuilder();
        userInput.append("分析需求：").append("\n");
        String userGoal = goal;
        if (StringUtils.isNotBlank(chartType)) {
            userGoal += ", 请使用" + chartType;
        }
        userInput.append(userGoal).append("\n");
        userInput.append("原始数据：").append("\n");
        String chartData = ExcelUtils.excelToCsv(multipartFile);
        userInput.append(chartData).append("\n");

        Chart chart = new Chart();
        chart.setName(name);
        chart.setGoal(goal);
        chart.setChartData(chartData);
        chart.setChartType(chartType);
        chart.setStatus("wait");
        chart.setUserId(loginUser.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("userInput",userInput.toString());
        map.put("chart",chart);

        boolean saveResultWithOutGenChart = chartService.save(chart);
        ThrowUtils.throwIf(!saveResultWithOutGenChart, ErrorCode.SYSTEM_ERROR, "图标保存失败");
        rabbitTemplate.convertAndSend(RabbitMqConstant.RABBIT_EXCHANGE_DIRECT,RabbitMqConstant.RABBIT_ROUTING,map);
        BiResponse biResponse = new BiResponse();
        biResponse.setChartId(chart.getId());
        return ResultUtils.success(biResponse);
    }



}