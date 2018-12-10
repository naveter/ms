package com.kaviddiss.streamkafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import com.kaviddiss.streamkafka.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.kaviddiss.streamkafka.config.StreamsConfig.CAT_SERVICE;

@Component
@Slf4j
public class GreetingsListener {

    @Autowired
    private GreetingsController greetingsController;

    private ObjectMapper mapper = new ObjectMapper();

    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings) {
        if (!greetings.getSname().equals(CAT_SERVICE)) {
            return;
        }

        log.info("Received greetings: {}", greetings);

        switch(greetings.getMname()) {
            case "createUpdateCat":
                CategoryDAO cat = mapper.convertValue(greetings.getObject(), CategoryDAO.class);
                greetingsController.createUpdateCat(cat);
                break;
            case "getCat":
                Long id = mapper.convertValue(greetings.getObject(), Long.class);
                greetingsController.getCat(id);
                break;
            case "getAllCat":
                greetingsController.getAllCat();
                break;
            default:
                log.info("Unknown mname!");
                break;
        }
    }
}
