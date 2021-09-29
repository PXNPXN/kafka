package com.cunshan.kafkademo;

import com.cunshan.kafkademo.message.Demo08Message;
import com.cunshan.kafkademo.producer.Demo08Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo08ProducerTest {

    @Autowired
    private Demo08Producer producer;
    @Resource
    private KafkaTemplate<Object,Object> kafkaTemplate;

    @Test
    public void testSyncSendInTransaction() throws Exception {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSendInTransaction(id, new Runnable() {
            @Override
            public void run() {
                log.info("[run][我要开始睡觉了]");
                try{
                    Thread.sleep(10 * 1000L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                log.info("[run][我要睡醒了]");
            }
        });

        new CountDownLatch(1).await();
    }

    @Test
    @Transactional
    public void transactionTest() throws Exception {
        int id = (int) (System.currentTimeMillis() / 1000);
        Demo08Message message = new Demo08Message();
        message.setId(id);
        kafkaTemplate.send(Demo08Message.TOPIC,message);
    }

}
