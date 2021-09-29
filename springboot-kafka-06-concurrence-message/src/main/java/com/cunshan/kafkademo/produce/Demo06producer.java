package com.cunshan.kafkademo.produce;

import com.cunshan.kafkademo.message.Demo06Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Demo06producer {
    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws Exception {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        return kafkaTemplate.send(Demo06Message.TOPIC,message).get();
    }
}
