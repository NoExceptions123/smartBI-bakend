package com.lft.smart_bi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Pakage com.lft.smart_bi.annotation
 * @auther 邮专第一深情
 * @date 2023/7/8
 * @Description 权限校验自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须有某个角色
     *
     * @return
     */
    String mustRole() default "";

}

