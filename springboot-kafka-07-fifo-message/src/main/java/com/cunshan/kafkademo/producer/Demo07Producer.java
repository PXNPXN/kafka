package com.cunshan.kafkademo.producer;

import com.cunshan.kafkademo.message.Demo07Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Demo07Producer {

    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult sysnSend(Integer id) throws Exception {
        Demo07Message message = new Demo07Message();
        message.setId(id);
        return kafkaTemplate.send(Demo07Message.TOPIC,String.valueOf(id),message).get();
    }

}
