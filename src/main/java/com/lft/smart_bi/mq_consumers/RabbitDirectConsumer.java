package com.lft.smart_bi.mq_consumers;

import com.lft.smart_bi.constant.RabbitMqConstant;
import com.lft.smart_bi.manager.AiManager;
import com.lft.smart_bi.model.entity.Chart;
import com.lft.smart_bi.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @pakage com.lft.smart_bi.mq_consumers
 * @auther 邮专第一深情
 * @Date 2023/7/26 10:46
 * @Descripetion
 */
@Component
@RabbitListener(queues = RabbitMqConstant.RABBIT_QUEUE)
@Slf4j
public class RabbitDirectConsumer {

    @Resource
    private ChartService chartService;
    @Resource
    private AiManager aiManager;

    @RabbitHandler
    public void genChat(Map<String, Object> map) {
        String userInput = (String) map.get("userInput");
        Chart chart = (Chart) map.get("chart");
        Chart updateChart = new Chart();
        updateChart.setId(chart.getId());
        updateChart.setStatus("running");
        boolean b = chartService.updateById(updateChart);
        if (!b) {
            handleChartUpdateError(chart.getId(), "更新图表执行中错误");
            return;
        }
        long bimoudleId = 1659171950288818178L;
        String result = aiManager.doChart(bimoudleId, userInput);
        String[] split = result.split("【【【【【");
        if (split.length < 3) {
            handleChartUpdateError(chart.getId(), "AI生成错误");
            return;
        }
        String genChart = split[1].trim();
        String genRsult = split[2].trim();
        Chart updateChartResult = new Chart();
        updateChartResult.setId(chart.getId());
        updateChartResult.setGenChart(genChart);
        updateChartResult.setGenResult(genRsult);
        updateChartResult.setStatus("succeed");
        boolean updateResult = chartService.updateById(updateChartResult);
        if (!updateResult) {
            handleChartUpdateError(chart.getId(), "更新图表AI生成失败");
        }

    }

    private void handleChartUpdateError(Long chartId, String message) {
        Chart chart = new Chart();
        chart.setId(chartId);
        chart.setStatus("failed");
        chart.setExecMessage(message);
        boolean b = chartService.updateById(chart);
        if (!b) {
            log.error("更新图表失败" + chartId + "," + message);
        }
    }
}
