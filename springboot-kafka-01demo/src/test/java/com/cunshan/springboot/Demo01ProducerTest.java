package com.cunshan.springboot;

import com.cunshan.springboot.lab01.kafkademo.Application;
import com.cunshan.springboot.lab01.kafkademo.producer.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Demo01ProducerTest {
    @Autowired
    private Demo01Producer producer;

    private static CountDownLatch count = new CountDownLatch(1);

    @Test
    public void testSyncSend() throws Exception{
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]",id,result);

        //阻塞等待，保证消费
        count.await();
    }

    @Test
    public void testASyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);

        producer.asynSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]",id,e);
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]",id,result);
                count.countDown();
            }
        });

        //阻塞等待，保证消费
        count.await();
    }
}
