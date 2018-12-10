package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCatId(Long id);

    List<Article> findByUserId(Long id);

}
