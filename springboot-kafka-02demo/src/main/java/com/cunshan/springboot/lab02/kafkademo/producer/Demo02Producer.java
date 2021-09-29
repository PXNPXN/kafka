package com.cunshan.springboot.lab02.kafkademo.producer;

import com.cunshan.springboot.lab02.kafkademo.message.Demo02Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Component
public class Demo02Producer {

    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object,Object>> asyncSend(Integer id){
        Demo02Message message = new Demo02Message();
        message.setId(id);
        return kafkaTemplate.send(Demo02Message.TOPIC,message);
    }

}
