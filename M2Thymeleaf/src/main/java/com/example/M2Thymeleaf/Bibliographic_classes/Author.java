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
    private String birthyear;

    public Author(String author) {

        // TODO create proper atributes
        setAuthor_id(null);
        setBirthyear("0000");
        setComplete_name(author);
    }

    public Long getAuthor_id() {
        return Author_id;
    }

    public String getComplete_name() {return complete_name;}

    public String getBirthyear() {return birthyear;}

    public void setComplete_name(String complete_name) {
        this.complete_name = complete_name;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public void setAuthor_id(Long author_id) {
        Author_id = author_id;
    }

    public Author() {
    }

    public String toStringcustom() {
        return "Author{" +
                "; Author_id=" + Author_id +
                "; complete_name='" + complete_name + '\'' +
                "; birth_year='" + birthyear + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Author{" +
                ", Author_id=" + Author_id +
                ", complete_name='" + complete_name + '\'' +
                ", birth_year='" + birthyear + '\'' +
                '}';
    }

    public void print_atrib() {
        String atribs = toStringcustom();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }
    public void setatribs(String atrib, String type) {

        if (type.toLowerCase().contains("name"))
            setComplete_name(atrib);
        else if (type.toLowerCase().contains("birth"))
            setBirthyear(atrib);
        else
            System.out.println("No change made in author's attributes");
    }
}
