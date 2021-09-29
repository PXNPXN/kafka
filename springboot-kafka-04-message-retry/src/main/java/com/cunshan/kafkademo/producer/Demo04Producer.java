package com.cunshan.kafkademo.producer;

import com.cunshan.kafkademo.message.Demo04Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Component
public class Demo04Producer {

    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    // 同步发送消息
    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        Demo04Message message = new Demo04Message();
        message.setId(id);
        return kafkaTemplate.send(Demo04Message.TOPIC,message).get();
    }

}
