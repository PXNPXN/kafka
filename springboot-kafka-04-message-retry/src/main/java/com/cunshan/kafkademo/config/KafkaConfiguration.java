package com.cunshan.kafkademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;


@Configuration
public class KafkaConfiguration {

    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?,?> template) {
        // 1. 创建 DeadLetterPublishingRecoverer 对象 ：重试到达最大次数时，Consumer 还是消费失败时，该消息就会发送到死信队列
        ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
        // 2. 创建 FixedBackOff 对象  消费消息重试次数， 固定为 3 次   interval: 是两次尝试的间隔
        BackOff backOff = new FixedBackOff(5 * 1000L, 3L);
        // 3. 创建 SeekToCurrentErrorHandler 对象   负责处理异常，串联整个消费重试的整个过程
        return new SeekToCurrentErrorHandler(recoverer, backOff);
    }
}
