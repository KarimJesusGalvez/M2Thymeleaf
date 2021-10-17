package com.example.M2Thymeleaf.Repos;

import com.example.M2Thymeleaf.Bibliographic_classes.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Author_Repo extends JpaRepository<Author,Long> {
    public List<Author> findByBirthyear(String year);
}
