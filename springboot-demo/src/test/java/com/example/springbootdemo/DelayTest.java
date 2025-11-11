package com.example.springbootdemo;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.example.springbootdemo.delay.Provider8;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-11 11:14
 * @Description: 延迟消息测试
 */
@Slf4j
@SpringBootTest
public class DelayTest {

    @Resource(name = "provider8")
    private Provider8 provider;

    @Test
    public void test() throws InterruptedException {
        provider.sendMessage("hello world", RabbitMqConfig.SP_DIRECT_ROUTING_KEY);
        TimeUnit.MINUTES.sleep(2);
    }
}
