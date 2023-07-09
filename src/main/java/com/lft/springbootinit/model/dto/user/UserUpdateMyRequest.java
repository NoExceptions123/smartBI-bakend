package com.lft.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
*@Pakage com.lft.springbootinit.model.dto.user
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   用户更新个人信息请求
*/
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;


    private static final long serialVersionUID = 1L;
}