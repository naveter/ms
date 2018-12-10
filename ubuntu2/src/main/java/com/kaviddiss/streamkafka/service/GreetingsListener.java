package com.kaviddiss.streamkafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaviddiss.streamkafka.model.ArticleDAO;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import com.kaviddiss.streamkafka.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.kaviddiss.streamkafka.config.StreamsConfig.ART_SERVICE;

@Component
@Slf4j
public class GreetingsListener {

    @Autowired
    private GreetingsController greetingsController;

    private ObjectMapper mapper = new ObjectMapper();

    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings) {
        if (!greetings.getSname().equals(ART_SERVICE)) {
            return;
        }

        log.info("Received greetings: {}", greetings);

        switch(greetings.getMname()) {
            case "createUpdateArt":
                ArticleDAO art = mapper.convertValue(greetings.getObject(), ArticleDAO.class);
                greetingsController.createUpdateArt(art);
                break;
            case "getArt":
                Long id1 = mapper.convertValue(greetings.getObject(), Long.class);
                greetingsController.getArt(id1);
                break;
            case "getAllArt":
                greetingsController.getAllArt();
                break;
            case "getArtFromCat":
                Long id2 = mapper.convertValue(greetings.getObject(), Long.class);
                greetingsController.getArtFromCat(id2);
                break;
            case "deleteAllArticlesFromCategory":
                CategoryDAO cat = mapper.convertValue(greetings.getObject(), CategoryDAO.class);
                greetingsController.deleteAllArticlesFromCategory(cat);
                break;
            default:
                log.info("Unknown mname!");
                break;
        }
    }
}
