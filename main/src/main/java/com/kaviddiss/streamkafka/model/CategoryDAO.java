package com.kaviddiss.streamkafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// TODO: Нужен общий ресурс, где могли бы храниться общие модели
@Getter
@Setter
@ToString
@Builder
public class CategoryDAO {
    private Long id;
    private String name;
    private Boolean delete;
}
