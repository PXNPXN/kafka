package com.cunshan.springboot.lab02.kafkademo.message;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Demo02Message {

    public static final String TOPIC = "topic-create";

    private Integer id;
}
