package com.lft.smart_bi.config;


import com.lft.smart_bi.constant.RabbitMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @pakage com.lft.smart_bi.config
 * @auther 邮专第一深情
 * @Date 2023/7/26 10:15
 * @Descripetion
 */
@Configuration
public class RabbitMqConfig implements BeanPostProcessor {

    @Bean
    public Queue rabbitDirectQueue() {
        return new Queue(RabbitMqConstant.RABBIT_QUEUE, true, false, false);
    }

    @Bean
    public DirectExchange rabbitDirectExchange() {
        return new DirectExchange(RabbitMqConstant.RABBIT_EXCHANGE_DIRECT, true, false);
    }


    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitDirectQueue())
                //到交换机
                .to(rabbitDirectExchange())
                //并设置匹配键
                .with(RabbitMqConstant.RABBIT_ROUTING);
    }

}
