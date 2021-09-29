package com.cunshan.springboot.lab01.kafkademo.consumer;

import com.cunshan.springboot.lab01.kafkademo.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo01AConsumer {
    @KafkaListener(topics = Demo01Message.TOPIC,groupId = "demo01-A-consumer-group-"+Demo01Message.TOPIC)
    public void onMessage(ConsumerRecord<Integer,String> record){
        log.info("[onMessage][线程编号：{} 消费内容：{}]",Thread.currentThread().getId(),record);
    }
}
