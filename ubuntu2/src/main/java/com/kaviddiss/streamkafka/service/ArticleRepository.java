package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCatId(Long id);

    List<Article> findByUserId(Long id);

    @Modifying
    @Transactional
//    @Query("delete from User u where u.active = false")
    Long deleteArticleByCatId(Long catId);

}
