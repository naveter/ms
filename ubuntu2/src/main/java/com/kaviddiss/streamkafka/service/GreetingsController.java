package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.Article;
import com.kaviddiss.streamkafka.model.ArticleDAO;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kaviddiss.streamkafka.config.StreamsConfig.*;

@Service
@Slf4j
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @Autowired
    private ArticleRepository articleRepository;

    public void createUpdateArt(ArticleDAO art) {
        log.info("createUpdateArt");

        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("createUpdateArt")
                .object(art)
                .build();

        // TODO: Тут нужно проверять существует ли указанная категория

        if (art.getId() != null) {
            Article a = articleRepository.findOne(art.getId());
            if(a == null) {
                greetings.setCode(CODE_ERROR);
                greetings.setMessage("Указанной статьи не существует");
            }
            else if (art.getDelete().equals(true)) {
                articleRepository.delete(a);
                greetings.setCode(CODE_DELETED);
                greetings.setMessage("Статья удалена");
            }
            else {
                a.setCatId(art.getCatId());
                a.setUserId(art.getUserId());
                a.setTitle(art.getTitle());
                a.setBody(art.getBody());

                articleRepository.saveAndFlush(a);
                greetings.setCode(CODE_UPDATED);
                greetings.setMessage("Статья обновлена");
            }
        }
        else {
            Article a = new Article();
            a.setCatId(art.getCatId());
            a.setUserId(art.getUserId());
            a.setTitle(art.getTitle());
            a.setBody(art.getBody());
            a.setCreated(new Date());

            a = articleRepository.saveAndFlush(a);
            art.setId(a.getId());
            greetings.setCode(CODE_ADDED);
            greetings.setMessage("Статья добавлена");
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

    public void getArt(Long id) {
        log.info("getArt");
        if (id == null) {
            return;
        }

        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("getArt")
                .build();

        Article a = articleRepository.findOne(id);

        if (a == null) {
            greetings.setCode(CODE_ERROR);
            greetings.setMessage("Указанной статьи не существует");
        }
        else {
            ArticleDAO art = ArticleDAO.builder().id(a.getId()).catId(a.getCatId()).userId(a.getUserId())
                    .title(a.getTitle()).body(a.getBody()).created(a.getCreated()).build();

            greetings.setObject(art);
            greetings.setMessage("Статья получена");
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

    public void getAllArt() {
        log.info("getAllArt");

        List<ArticleDAO> list = new ArrayList<>();
        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("getAllArt")
                .object(list)
                .build();

        List<Article> findAllList = articleRepository.findAll();

        if (findAllList.isEmpty()) {
            greetings.setCode(CODE_ERROR);
            greetings.setMessage("Нет ни одной статьи");
        }
        else {
            findAllList.stream().forEach(a ->
                list.add(ArticleDAO.builder().id(a.getId()).catId(a.getCatId()).userId(a.getUserId())
                        .title(a.getTitle()).body(a.getBody()).created(a.getCreated()).build())
            );
            greetings.setMessage("Статьи получены");
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

    public void getArtFromCat(Long catId) {
        log.info("getArtFromCat");
        if (catId == null) {
            return;
        }

        List<ArticleDAO> list = new ArrayList<>();
        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("getArtFromCat")
                .object(list)
                .build();

        List<Article> findAllList = articleRepository.findByCatId(catId);

        if (findAllList.isEmpty()) {
            greetings.setMessage("Нет ни одной статьи из категории " + catId);
        }
        else {
            findAllList.stream().forEach(a ->
                    list.add(ArticleDAO.builder().id(a.getId()).catId(a.getCatId()).userId(a.getUserId())
                            .title(a.getTitle()).body(a.getBody()).created(a.getCreated()).build())
            );
            greetings.setMessage("Статьи категории " + catId + " получены");
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

    public void deleteAllArticlesFromCategory(CategoryDAO cat) {
        log.info("deleteAllArticlesFromCategory");
        if (cat == null) {
            return;
        }

        Long count = articleRepository.deleteArticleByCatId(cat.getId());
        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("deleteAllArticlesFromCategory")
                .message("Удалено " + count + " статей категории " + cat.getName())
                .object(cat)
                .build();

        greetingsService.sendGreeting(greetings);
    }

}
