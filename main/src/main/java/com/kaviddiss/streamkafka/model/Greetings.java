package com.kaviddiss.streamkafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.kaviddiss.streamkafka.config.StreamsConfig.CODE_SUCCESS;

@Getter @Setter @ToString @Builder
public class Greetings {
    private String sname;
    private String mname;
    private Object object;
    private String message = "";
    private String code = CODE_SUCCESS;
//    private Greetings next;
}
