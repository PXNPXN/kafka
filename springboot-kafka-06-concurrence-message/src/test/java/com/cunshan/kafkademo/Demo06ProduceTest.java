package com.cunshan.kafkademo;

import com.cunshan.kafkademo.produce.Demo06producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Demo06ProduceTest {

    @Autowired
    private Demo06producer producer;


    /**
     * 该测试是 并发消费demo 即通过在消费者端 设置 concurrency = "2" 来设置消费的并发线程数
     * spring-kafka 就会为该 @KafkaListener 创建 4 个线程并发消费
     * @throws Exception
     */
    @Test
    public void testSyncSend() throws Exception {
        for(int i=0;i<10;i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            SendResult result = producer.syncSend(id);
            log.info("[testSyncSend][发送编号: [{}] 发送结果: [{}]]",id,result);
        }

        //阻塞等待，保证消费
        new CountDownLatch(1).await();

    }

}
