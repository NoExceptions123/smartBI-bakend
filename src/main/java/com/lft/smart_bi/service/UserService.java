package com.lft.smart_bi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lft.smart_bi.model.dto.user.UserQueryRequest;
import com.lft.smart_bi.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lft.smart_bi.model.vo.LoginUserVO;
import com.lft.smart_bi.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 1213
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2023-07-09 10:38:45
 */
public interface UserService extends IService<User> {
    long userRegister(String userAccount, String userPassword, String checkPassword);

    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);

    UserVO getUserVO(User user);

    List<UserVO> getUserVOList(List<User> userList);

    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    boolean isAdmin(User user);
}
