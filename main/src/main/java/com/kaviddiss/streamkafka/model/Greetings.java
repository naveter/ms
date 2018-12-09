package com.kaviddiss.streamkafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder
public class Greetings {
    private String sname;
    private String mname;
    private Object object;
    private String message;
    private String code;
//    private Greetings next;
}
