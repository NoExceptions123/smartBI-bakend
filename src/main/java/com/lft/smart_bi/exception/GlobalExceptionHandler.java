package com.lft.smart_bi.exception;

import com.lft.smart_bi.common.BaseResponse;
import com.lft.smart_bi.common.ErrorCode;
import com.lft.smart_bi.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
*@Pakage com.lft.smart_bi.exception
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   全局异常处理器
*/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
