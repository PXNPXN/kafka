package com.cunshan.kafkademo.message;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Accessors(chain = true)
public class Demo06Message {
    public static final String TOPIC = "demo06";

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
