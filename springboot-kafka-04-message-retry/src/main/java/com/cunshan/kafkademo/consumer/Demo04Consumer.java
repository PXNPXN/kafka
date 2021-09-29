package com.cunshan.kafkademo.consumer;

import com.cunshan.kafkademo.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class Demo04Consumer {
    private AtomicInteger count = new AtomicInteger(0);

    @KafkaListener(topics = Demo04Message.TOPIC,groupId = "demo04-consumer-group"+Demo04Message.TOPIC)
    public void onMessage(Demo04Message message) {
        log.info("[onMessage][线程编号：{} 消息内容：{}]",Thread.currentThread().getId(),message);
        // 此处抛出一个 RuntimeException 异常， 模拟消费失败
        throw new RuntimeException("故意抛出一个异常");  //有重试的操作，但是目前需要我debug然后手动调试
    }

}
