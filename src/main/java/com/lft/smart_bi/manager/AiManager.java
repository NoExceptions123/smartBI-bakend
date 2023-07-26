package com.lft.smart_bi.manager;

import com.lft.smart_bi.common.ErrorCode;
import com.lft.smart_bi.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Pakage com.lft.smart_bi.manager
 * @auther 邮专第一深情
 * @date 2023/07/12 20:55
 */
@Component
public class AiManager {
    @Resource
    private YuCongMingClient yuCongMingClient;

    public String doChart(long modleId, String message) {
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modleId);
        devChatRequest.setMessage(message);
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        if (response == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI 响应错误");
        }
        return response.getData().getContent();
    }
}
