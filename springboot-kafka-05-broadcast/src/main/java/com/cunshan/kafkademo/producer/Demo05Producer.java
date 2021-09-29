package com.cunshan.kafkademo.producer;

import com.cunshan.kafkademo.message.Demo05Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Demo05Producer {

    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws Exception {
        Demo05Message message = new Demo05Message();
        message.setId(id);
        // 这里同步发送 kafka 中的消息
        return kafkaTemplate.send(Demo05Message.TOPIC,message).get();
    }

}
