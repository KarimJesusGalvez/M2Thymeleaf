package com.example.M2Thymeleaf.Repos;

import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface B_E_Repo extends JpaRepository<Bibliographic_entry,Long> {
    List<Bibliographic_entry> findByType(String type);
}
