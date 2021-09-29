package com.cunshan.kafkademo;

import com.cunshan.kafkademo.producer.Demo05Producer;
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
public class Demo05ProducerTest {

    @Autowired
    private Demo05Producer producer;

    @Test
    public void test() throws Exception {
        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSend() throws Exception {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号:[{}] 发送结果：[{}]]",id,result);
        new CountDownLatch(1).await();
    }

}
