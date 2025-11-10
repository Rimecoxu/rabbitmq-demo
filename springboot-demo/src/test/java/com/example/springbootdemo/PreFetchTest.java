package com.example.springbootdemo;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.example.springbootdemo.prefetch.Provider4;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:41
 * @Description: 消息预获取测试
 */
@Slf4j
@SpringBootTest
public class PreFetchTest {

    @Resource(name = "provider4")
    private Provider4 provider;

    @Test
    void process() throws InterruptedException {
        CorrelationData correlationData = new CorrelationData();
        for (int i = 1; i <= 30; i++) {
            String message = "Hello World RabbitMQ-" + i;
            correlationData.setId(String.valueOf(i));
            provider.sendMessage(message, RabbitMqConfig.SP_DIRECT_ROUTING_KEY, correlationData);
        }
        // TimeUnit.MINUTES.sleep(2);
    }
}
