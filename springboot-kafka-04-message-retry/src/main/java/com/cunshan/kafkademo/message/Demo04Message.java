package com.cunshan.kafkademo.message;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
public class Demo04Message {

    public static final String TOPIC = "topic-demo";

    private Integer id;

}
