package com.cunshan.kafkademo.consumer;

import com.cunshan.kafkademo.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Demo05Consumer {

    @KafkaListener(topics = Demo05Message.TOPIC,
            groupId = "demo05-consumer-group-"+Demo05Message.TOPIC+"-"+"#{T(java.util.UUID).randomUUID()}") //<X>
    public void onMessage(Demo05Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]",Thread.currentThread().getId(),message.getId());
    }
}
