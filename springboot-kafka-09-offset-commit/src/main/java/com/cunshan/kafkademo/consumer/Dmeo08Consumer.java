package com.cunshan.kafkademo.consumer;

import com.cunshan.kafkademo.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Dmeo08Consumer {

    @KafkaListener(topics = Demo08Message.TOPIC,groupId = "demo08-consumer-groop-"+Demo08Message.TOPIC)
    public void onMessage(Demo08Message message, Acknowledgment acknowledgment) {
        log.info("[onMessage][线程编号:{} 消息内容: {}]",Thread.currentThread().getId(),message);

        if(message.getId() % 2 == 1) { // 这里只提交基数id的消息的消费位移
            acknowledgment.acknowledge();
        }

    }

}
