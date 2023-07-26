package com.lft.smart_bi.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Pakage com.lft.smart_bi.config
 * @auther 邮专第一深情
 * @date 2023/07/16 15:06
 */

@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
@Data
public class RedissonConfig {
    private Integer database;
    private String host;
    private String port;

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(database)
                .setAddress("redis://" + host + ":" + port);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
