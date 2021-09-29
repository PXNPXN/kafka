package com.cunshan.springboot.lab01.kafkademo.message;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Demo01Message {
    public static final String TOPIC = "topic-create";

    /**
     * 编号
     */
    private Integer id;
}
