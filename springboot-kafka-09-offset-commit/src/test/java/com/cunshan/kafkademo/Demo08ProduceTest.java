package com.cunshan.kafkademo;

import com.cunshan.kafkademo.producer.Demo08Producer;
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
public class Demo08ProduceTest {

    @Autowired
    private Demo08Producer producer;

    /**
     * 该方法主要是用于测试手动位移提交，首先我们需要在 application.yaml 文件中进行相对应的配置：
     *   1. enable-auto-commit: false   自动提交配置项设置为 false 则表示手动提交
     *   2. spring-kafka 下提供了一个 AckMode 枚举类 ，该类提供了更丰富的消费者进度的提交机制，更加灵活(分为两大类： 1. 自动提交和手动提交)
     *      RECORD -> 每条消息被消费完成后，自动提交
     *      BATCH -> 每次消息被消费完成后，在下次拉取消息之前，自动提交
     *      TIME -> 达到一定时间间隔后，自动提交
     *      COUNT -> 消费成功的消息到达一定数量后，自动提交
     *      COUNT_TIME -> TIME 和 COUNT 的结合体，满足任一都会自动提交
     *      MANUAL -> 调用时，先标记提交消费进度，等到当前消息被消费完成，然后再提交消费进度
     *      MANUAL_IMMEDIATE -> 调用时，立即提交费用进度
     *   3. 在 listener 下 配置 ack-mode: manual
     *
     *   结果：最后生产者会发送两条消息，而消费者会接收到两条消息，但是只会提交一条消息的消费位移
     *
     *   问题： kafka-manager 还没有安装完成，是否只提交了一次消费位移也没有验证
     * @throws Exception
     */
    @Test
    public void syncSendTest() throws Exception {
        //生产者向kafka发送两条消息
        for (int i=1;i<=2;i++) {
           SendResult result =  producer.syncSend(i);
           log.info("[testSyncSend][发送编号：[{}] 发送结果: [{}]",i,result);
        }

        new CountDownLatch(1).await();
    }

}
