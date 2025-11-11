package com.example.springbootdemo.object;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-11 11:56
 * @Description: 对象消息测试
 */
@Slf4j
@SpringBootTest
public class ConsumerTest {

    @Test
    public void test() throws InterruptedException {
        log.info("对象消息消费者正在接收: ");
        Thread.sleep(60 * 60 * 1000L);
    }
}
