package com.lft.smart_bi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Pakage com.lft.smart_bi.config
 * @auther 邮专第一深情
 * @date 2023/7/8
 * @Description Knife4j 接口文档配置
 * https://doc.xiaominfo.com/knife4j/documentation/get_start.html
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class Knife4jConfig {

    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("接口文档")
                        .description("smart_bi")
                        .version("3.0")
                        .build())
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lft.smart_bi.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}