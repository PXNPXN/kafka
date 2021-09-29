package com.cunshan.kafkademo.consumer;

import com.cunshan.kafkademo.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Demo08Consumer {

    @KafkaListener(topics = Demo08Message.TOPIC,groupId = "demo08-consumer-group-"+Demo08Message.TOPIC)
    public void onMessage(Demo08Message message) throws Exception {
        log.info("[onMessage][线程编号: {} 消息内容: {}]",Thread.currentThread().getId(),message);
    }

}
