package com.cunshan.springboot.lab01.kafkademo.producer;

import com.cunshan.springboot.lab01.kafkademo.message.Demo01Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

@Component
public class Demo01Producer {

    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws Exception {
        Demo01Message me = new Demo01Message();
        me.setId(id);
        //同步发送
        return kafkaTemplate.send(Demo01Message.TOPIC,me).get();
    }

    //返回 Spring ListenableFuture对象，一个可以通过监听结果的Future增强
    public ListenableFuture<SendResult<Object, Object>> asynSend(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //异步发送
        return kafkaTemplate.send(Demo01Message.TOPIC,message);
    }

}
