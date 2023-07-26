package com.lft.smart_bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
*@Pakage com.lft.smart_bi
*@auther 邮专第一深情
*@date 2023/7/8
*@Description   主类（项目启动入口）
*/
// todo 如需开启 Redis，须移除 exclude 中的内容
@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
    
}
