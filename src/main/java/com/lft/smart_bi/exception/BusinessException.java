package com.lft.smart_bi.exception;

import com.lft.smart_bi.common.ErrorCode;

/**
*@Pakage com.lft.smart_bi.exception
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   自定义异常类
*/
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
