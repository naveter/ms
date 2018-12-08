package com.kaviddiss.streamkafka;

import com.kaviddiss.streamkafka.model.Article;
import com.kaviddiss.streamkafka.service.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class StreamKafkaApplication {

    @Autowired
    private ArticleRepository articleRepository;

    public StreamKafkaApplication() {

    }

    public void init() {
        Article cat = this.articleRepository.findOne(1L);
        log.info(cat.toString());
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(StreamKafkaApplication.class)
                .headless(false).run(args);

        StreamKafkaApplication ex = ctx.getBean(StreamKafkaApplication.class);
        ex.init();

    }

}
