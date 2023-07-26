package com.lft.smart_bi.model.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@Pakage com.lft.smart_bi.model.vo
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   用户视图（脱敏）
*/
@Data
public class UserVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}