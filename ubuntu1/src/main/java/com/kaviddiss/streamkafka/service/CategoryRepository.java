package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    Category findByNameAndIdNot(String name, Long id);
}
