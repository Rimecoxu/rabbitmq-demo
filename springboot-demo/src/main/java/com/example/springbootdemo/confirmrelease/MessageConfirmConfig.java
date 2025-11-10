package com.example.springbootdemo.confirmrelease;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-05 18:17
 * @Description: 消息发布确认配置
 */
@Slf4j
// @Configuration
public class MessageConfirmConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 配置发布消息确认交当前对象处理 (MessageConfirmConfig对象)
        rabbitTemplate.setConfirmCallback(this);
        // 配置消息回退处理交当前对象处理
        rabbitTemplate.setReturnsCallback(this);
        // 配置是否保留回退消息（默认保留）
        rabbitTemplate.setMandatory(true);
    }


    /**
     * 消息确认回调
     *
     * @param correlationData 消息相关信息
     * @param ack             消息投递交换机是否成功
     * @param cause           投递失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            // 打印消息id（生产者设置）
            log.info("消息投递成功：{}", correlationData.getId());
        } else {
            log.info("消息投递失败: {}", cause);
        }
    }

    /**
     * 投递失败回调
     *
     * @param returned 消息相关信息
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("消息投递失败, 消息: {}", new String(returned.getMessage().getBody()));
        log.info("失败原因: {}", returned.getReplyText());
        log.info("交换机: {}", returned.getExchange());
        log.info("路由键: {}", returned.getRoutingKey());
    }

}
