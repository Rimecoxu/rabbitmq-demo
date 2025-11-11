package com.example.springbootdemo.object.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-11 12:35
 * @Description: 配置RabbitTemplate
 */
@Configuration
public class RabbitTemplateConfig {

    /**
     * 使用Jackson2JsonMessageConverter 配置RabbitTemplate
     *
     * @param connectionFactory 连接工厂
     * @return RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 使用Jackson2JsonMessageConverter自定义消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * 使用Jackson2JsonMessageConverter 配置RabbitListenerContainerFactory
     *
     * @param connectionFactory 连接工厂
     * @return RabbitListenerContainerFactory
     */
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 使用Jackson2JsonMessageConverter自定义消息转换器
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
