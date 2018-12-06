package com.kaviddiss.streamkafka.service;

import com.kaviddiss.streamkafka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Long id);
}
