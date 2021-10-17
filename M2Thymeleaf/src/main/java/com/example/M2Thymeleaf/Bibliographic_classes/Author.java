package com.example.M2Thymeleaf.Bibliographic_classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Author_id;

    private String complete_name;
    private String birth_year;

    public Author(String author) {

        // TODO create proper atributes
        setAuthor_id(null);
        setBirth_year("0000");
        setComplete_name(author);
    }


    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public void setAuthor_id(Long author_id) {
        Author_id = author_id;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "; Author_id=" + Author_id +
                "; complete_name='" + complete_name + '\'' +
                "; birth_year='" + birth_year + '\'' +
                '}';
    }
}
