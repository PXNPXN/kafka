package com.cunshan.kafkademo;

import com.cunshan.kafkademo.producer.Demo07Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo07ProducerTest {

    @Autowired
    private Demo07Producer producer;

    /**
     * 该测试方法用于测试顺序消费，我们将 id = 1 作为每个消息的 key，
     * 然后发送 10 条数据，这10条数据由于 key 相同，会被分配到同一个 Partition 中
     * @throws Exception
     */
    @Test
    public void testSyncSendOrDerly() throws Exception {
        for (int i=0;i<10;i++) {
            int id = 1;
            SendResult result = producer.sysnSend(id);
            log.info("[testSyncSend][发送者编号:[{}] 发送队列: [{}]]",id,result.getRecordMetadata().partition());
        }

        new CountDownLatch(1).await();
    }

}
