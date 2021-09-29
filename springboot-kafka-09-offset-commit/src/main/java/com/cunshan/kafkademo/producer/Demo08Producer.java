package com.cunshan.kafkademo.producer;

import com.cunshan.kafkademo.message.Demo08Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Demo08Producer {

    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws Exception {
        Demo08Message message = new Demo08Message();
        message.setId(id);
        return kafkaTemplate.send(Demo08Message.TOPIC,message).get();
    }

}
