package com.cunshan.kafkademo.producer;

import com.cunshan.kafkademo.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class Demo08Producer {

    /**
     * 该方法主要用于测试 spring-kafka 的 Transaction 的用法
     *    Transaction的用法主要有两种
     *      1. kafkaTemplate.executeInTransaction() 方法，需要重写 doInOperations() 方法 并将事务逻辑写在该方法中
     *      2. 通过使用 @Transactional spring 提供的事务注解
     * 另外，我们在使用 kafka 的事务时，需要在 application.yaml 配置文件中配置相关配置：
     *     1. 在 producer 中 配置
     */
    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public String syncSendInTransaction(Integer id, Runnable runnable) throws ExecutionException,InterruptedException {
        return kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<Object, Object, String>() {
            @Override
            public String doInOperations(KafkaOperations<Object, Object> kafkaOperations) {
                Demo08Message message = new Demo08Message();
                message.setId(id);
                try{
                    SendResult<Object, Object> result = kafkaOperations.send(Demo08Message.TOPIC, message).get();
                    log.info("[doInOperations][发送编号: [{}] 发送结果：[{}]]",id,result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                runnable.run();

                return "success";
            }
        });
    }
}
