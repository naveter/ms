package com.kaviddiss.streamkafka.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaviddiss.streamkafka.MainJFrame;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kaviddiss.streamkafka.config.StreamsConfig.CAT_SERVICE;
import static com.kaviddiss.streamkafka.config.StreamsConfig.CODE_DELETED;
import static com.kaviddiss.streamkafka.config.StreamsConfig.CODE_ERROR;

@Service
@Slf4j
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainJFrame mainJFrame;

    /**
     * Создание\обновление категории
     *
     * @param id
     * @param name
     */
    public void createUpdateCat(Long id, String name, Boolean delete) {
        log.info("createUpdateCat");
        CategoryDAO cat = CategoryDAO.builder().id(id).name(name).delete(delete).build();

        Greetings greetings = Greetings.builder()
            .sname(CAT_SERVICE)
            .mname("createUpdateCat")
            .object(cat)
            .build();

        greetingsService.sendGreeting(greetings);
    }

    /**
     * Уведомление о создании\обновлении категории
     *
     * @param greetings
     */
    public void createUpdateCat(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setCatInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_DELETED)) {
            // TODO: Тут поменять: в начале удалить ВСЕ статьи из категории, а уже затем саму категорию
        }

        mainJFrame.clearCatForm();
        this.getAllCat();
    }

    public void getCat(Long id) {
        log.info("getCat");

        Greetings greetings = Greetings.builder()
                .sname(CAT_SERVICE)
                .mname("getCat")
                .object(id)
                .build();

        greetingsService.sendGreeting(greetings);
    }

    public void getCat(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setCatInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }
    }

    public void getAllCat() {
        log.info("getAllCat");
        mainJFrame.setCatInfo("Загружаю все категории...");

        Greetings greetings = Greetings.builder()
                .sname(CAT_SERVICE)
                .mname("getAllCat")
                .build();

        greetingsService.sendGreeting(greetings);
    }

    public void getAllCat(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setCatInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<CategoryDAO> list = mapper.convertValue((List<CategoryDAO>)greetings.getObject(), new TypeReference<List<CategoryDAO>>(){});

        list.stream().forEach(c -> {
            log.info(c.toString());
        });

        mainJFrame.fillCatList(list);
    }

}
