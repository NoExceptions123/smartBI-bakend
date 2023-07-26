package com.lft.smart_bi.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
*@Pakage com.lft.smart_bi.model.dto.user
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   用户注册请求体
*/
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
