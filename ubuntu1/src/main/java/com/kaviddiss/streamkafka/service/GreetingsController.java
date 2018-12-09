package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.Category;
import com.kaviddiss.streamkafka.model.CategoryDAO;
import com.kaviddiss.streamkafka.model.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.kaviddiss.streamkafka.config.StreamsConfig.*;

@Service
@Slf4j
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @Autowired
    private CategoryRepository categoryRepository;

    public void createUpdateCat(CategoryDAO cat) {
        log.info("createUpdateCat");

        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("createUpdateCat")
                .object(cat)
                .build();

        if (cat.getId() != null) {
            Category c = categoryRepository.findOne(cat.getId());
            if(c == null) {
                greetings.setCode(CODE_ERROR);
                greetings.setMessage("Указанной категории не существует");
            }
            else if (cat.getDelete().equals(true)) {
                // TODO: Кто будет удалять записи и возможность транзакционности
                categoryRepository.delete(c);
                greetings.setCode(CODE_DELETED);
                greetings.setMessage("Категория удалена");
            }
            else if (categoryRepository.findByNameAndIdNot(cat.getName(), cat.getId()) != null) {
                greetings.setCode(CODE_ERROR);
                greetings.setMessage("Такое название уже есть в другой категории");
            }
            else {
                c.setName(cat.getName());
                categoryRepository.saveAndFlush(c);
                greetings.setCode(CODE_UPDATED);
                greetings.setMessage("Категория обновлена");
            }
        }
        else {
            if (categoryRepository.findByName(cat.getName()) != null) {
                greetings.setCode(CODE_ERROR);
                greetings.setMessage("Категория с таким названием уже существует");
            }
            else {
                Category newCat = new Category();
                newCat.setName(cat.getName());
                newCat = categoryRepository.saveAndFlush(newCat);
                cat.setId(newCat.getId());
                greetings.setCode(CODE_ADDED);
                greetings.setMessage("Категория добавлена");
            }
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

    public void getCat(Long id) {
        log.info("getCat");
        if (id == null) {
            return;
        }

        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("getCat")
                .build();

        Category c = categoryRepository.findOne(id);

        if (c == null) {
            greetings.setCode(CODE_ERROR);
            greetings.setMessage("Указанной категории не существует");
        }
        else {
            CategoryDAO cat = CategoryDAO.builder().id(c.getId()).name(c.getName()).build();
            greetings.setObject(cat);
            greetings.setMessage("Категория получена");
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

    public void getAllCat() {
        log.info("getAllCat");

        List<CategoryDAO> list = new ArrayList<>();
        Greetings greetings = Greetings.builder()
                .sname(MAIN_SERVICE)
                .mname("getAllCat")
                .object(list)
                .build();

        List<Category> findAllList = categoryRepository.findAll();

        if (findAllList.isEmpty()) {
            greetings.setCode(CODE_ERROR);
            greetings.setMessage("Нет ни одной категории");
        }
        else {
            findAllList.stream().forEach(c ->
                list.add(CategoryDAO.builder().id(c.getId()).name(c.getName()).build())
            );
            greetings.setMessage("Категории получены");
        }

        greetingsService.sendGreeting(greetings);
        log.info(greetings.getMessage());
    }

}
