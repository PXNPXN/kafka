package com.cunshan.kafkademo.consumer;

import com.cunshan.kafkademo.message.Demo06Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Demo06Consumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo06Message.TOPIC,concurrency = "2",
            groupId = "demo06-consumer-groop-" + Demo06Message.TOPIC)
    public void onMessage(Demo06Message message) {
         logger.info("[onMessage][线程编号: {}] 消息内容：{}]",Thread.currentThread().getId(),message);
    }

}
