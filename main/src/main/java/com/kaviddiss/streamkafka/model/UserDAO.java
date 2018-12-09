package com.kaviddiss.streamkafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDAO{
    private Long id;
    private String name;
}
