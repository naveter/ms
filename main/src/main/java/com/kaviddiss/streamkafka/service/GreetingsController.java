package com.kaviddiss.streamkafka.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaviddiss.streamkafka.MainJFrame;
import com.kaviddiss.streamkafka.model.ArticleDAO;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kaviddiss.streamkafka.config.StreamsConfig.*;

@Service
@Slf4j
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainJFrame mainJFrame;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Сообщение от Article что все статьи указанной категории удалены
     * и что можно послать запрос на удаление категории
     *
     * @param greetings
     */
    public void deleteAllArticlesFromCategory(Greetings greetings) {
        log.info("deleteAllArticlesFromCategory");
        CategoryDAO cat = mapper.convertValue(greetings.getObject(), CategoryDAO.class);

        Greetings greetings2 = Greetings.builder()
                .sname(CAT_SERVICE)
                .mname("createUpdateCat")
                .object(cat)
                .build();

        greetingsService.sendGreeting(greetings2);
    }

    /**
     * Создание\обновление категории
     *
     * @param id
     * @param name
     */
    public void createUpdateCat(Long id, String name, Boolean delete) {
        log.info("createUpdateCat");
        CategoryDAO cat = CategoryDAO.builder().id(id).name(name).delete(delete).build();
        Greetings greetings;

        if (delete.equals(true)) {
            greetings = Greetings.builder()
                    .sname(ART_SERVICE)
                    .mname("deleteAllArticlesFromCategory")
                    .object(cat)
                    .build();
        }
        else {
            greetings = Greetings.builder()
                    .sname(CAT_SERVICE)
                    .mname("createUpdateCat")
                    .object(cat)
                    .build();
        }

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

        // Очистить список статей
        if (greetings.getCode() != null && greetings.getCode().equals(CODE_DELETED)) {
            mainJFrame.clearArtList();
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
        List<CategoryDAO> list = mapper.convertValue(greetings.getObject(), new TypeReference<List<CategoryDAO>>(){});

        list.stream().forEach(c -> {
            log.info(c.toString());
        });

        mainJFrame.fillCatList(list);
    }


    public void getAllArt() {
        log.info("getAllArt");
        mainJFrame.setCatInfo("Загружаю все статьи...");

        Greetings greetings = Greetings.builder()
                .sname(ART_SERVICE)
                .mname("getAllArt")
                .build();

        greetingsService.sendGreeting(greetings);
    }

    /**
     * Уведомление о прибытии всех статей
     *
     * @param greetings
     */
    public void getAllArt(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setArtInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<ArticleDAO> list = mapper.convertValue(greetings.getObject(), new TypeReference<List<ArticleDAO>>(){});

        list.stream().forEach(a -> {
            log.info(a.getId().toString() + ", " + a.getTitle());
        });

        mainJFrame.fillArtList(list);
    }

    public void createUpdateArt(Long id, Long catId, Long userId, String title, String body, Boolean delete) {
        log.info("createUpdateArt");
        ArticleDAO art = ArticleDAO.builder().id(id).catId(catId).userId(userId)
                .title(title).body(body).delete(delete).build();
        Greetings greetings = Greetings.builder()
                    .sname(ART_SERVICE)
                    .mname("createUpdateArt")
                    .object(art)
                    .build();

        greetingsService.sendGreeting(greetings);
    }

    /**
     * Уведомление о создании\обновлении статьи
     *
     * @param greetings
     */
    public void createUpdateArt(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setArtInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }

        mainJFrame.clearArtForm();
        this.getAllArt();
    }

    public void getArt(Long id) {
        log.info("getArt");

        Greetings greetings = Greetings.builder()
                .sname(ART_SERVICE)
                .mname("getArt")
                .object(id)
                .build();

        greetingsService.sendGreeting(greetings);
    }

    public void getArt(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setArtInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }
    }

    public void getArtFromCat(Long id) {
        log.info("getArtFromCat");

        Greetings greetings = Greetings.builder()
                .sname(ART_SERVICE)
                .mname("getArtFromCat")
                .object(id)
                .build();

        greetingsService.sendGreeting(greetings);
    }

    public void getArtFromCat(Greetings greetings) {
        log.info(greetings.getMessage());
        mainJFrame.setArtInfo(greetings.getMessage());

        if (greetings.getCode() != null && greetings.getCode().equals(CODE_ERROR)) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<ArticleDAO> list = mapper.convertValue(greetings.getObject(), new TypeReference<List<ArticleDAO>>(){});

        list.stream().forEach(a -> {
            log.info(a.getId().toString() + ", " + a.getTitle());
        });

        mainJFrame.fillArtList(list);
    }
}
