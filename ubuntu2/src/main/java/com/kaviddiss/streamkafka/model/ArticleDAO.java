package com.kaviddiss.streamkafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

// TODO: Нужен общий ресурс, где могли бы храниться общие модели
@Getter
@Setter
@ToString
@Builder
public class ArticleDAO {

    private Long id;
    private Long userId;
    private Long catId;
    private String title;
    private String body;
    private Date created;
    private Boolean delete = new Boolean(false);

}
