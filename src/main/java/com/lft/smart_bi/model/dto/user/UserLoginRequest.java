package com.lft.smart_bi.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
*@Pakage com.lft.smart_bi.model.dto.user
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   用户登录请求
*/
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
