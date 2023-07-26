package com.lft.smart_bi.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
*@Pakage com.lft.smart_bi.model.dto.user
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   用户创建请求
*/
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}