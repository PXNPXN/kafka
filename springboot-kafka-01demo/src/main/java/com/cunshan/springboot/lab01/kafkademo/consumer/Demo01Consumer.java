package com.cunshan.springboot.lab01.kafkademo.consumer;

import com.cunshan.springboot.lab01.kafkademo.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo01Consumer {
    @KafkaListener(topics = Demo01Message.TOPIC,
            groupId = "demo01-consumer-group-"+Demo01Message.TOPIC)
    public void onMessage(Demo01Message message){
        log.info("[onMessage[[线程编号：{} 消费内容：{}]",Thread.currentThread().getId(),message);
    }
}
