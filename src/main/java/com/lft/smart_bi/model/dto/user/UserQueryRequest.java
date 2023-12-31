package com.lft.smart_bi.model.dto.user;

import com.lft.smart_bi.common.PageRequest;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@Pakage com.lft.smart_bi.model.dto.user
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   用户查询请求
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;



    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}