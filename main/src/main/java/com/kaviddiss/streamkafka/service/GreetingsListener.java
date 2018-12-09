package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import com.kaviddiss.streamkafka.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.kaviddiss.streamkafka.config.StreamsConfig.CAT_SERVICE;
import static com.kaviddiss.streamkafka.config.StreamsConfig.MAIN_SERVICE;

@Component
@Slf4j
public class GreetingsListener {

    @Autowired
    private GreetingsController greetingsController;

    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings) {
        log.info("Received greetings: {}", greetings);

        if (!greetings.getSname().equals(MAIN_SERVICE)) {
            return;
        }

        switch(greetings.getMname()) {
            case "createUpdateCat":
                greetingsController.createUpdateCat(greetings);
                break;
            case "getCat":
                greetingsController.getCat(greetings);
                break;
            case "getAllCat":
                greetingsController.getAllCat(greetings);
                break;
            default:
                log.info("Unknown mname!");
                break;
        }
    }
}
