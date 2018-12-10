package com.kaviddiss.streamkafka.config;

import com.kaviddiss.streamkafka.stream.GreetingsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
    // TODO: Нужен общий для всех ресурс с названиями сервисов
    public final static String CAT_SERVICE = "catService";
    public final static String ART_SERVICE = "artService";
    public final static String MAIN_SERVICE = "mainService";

    public final static String CODE_SUCCESS = "codeSuccess";
    public final static String CODE_ERROR = "codeError";
    public final static String CODE_UPDATED = "codeUpdated";
    public final static String CODE_ADDED = "codeAdded";
    public final static String CODE_DELETED = "codeDeleted";
}
