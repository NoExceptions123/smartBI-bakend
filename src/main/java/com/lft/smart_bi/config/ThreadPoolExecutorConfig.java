package com.lft.smart_bi.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Pakage com.lft.smart_bi.config
 * @auther 邮专第一深情
 * @date 2023/07/18 20:06
 */
@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private int count;

            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("线程" + count);
                count++;
                return thread;
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), threadFactory);
        return threadPoolExecutor;
    }

}
