package com.lft.springbootinit.common;

import java.io.Serializable;
import lombok.Data;

/**
*@Pakage com.lft.springbootinit.common
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   通用返回类
*/
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
