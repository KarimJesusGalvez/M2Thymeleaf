package com.example.M2Thymeleaf.Bibliographic_classes;

import com.example.M2Thymeleaf.Implementations.General_imp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bibliographic_entry {

    @OneToMany(fetch = FetchType.EAGER) //Resolves first to avoid access issues
    private List<Author> authorsList= new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long table_id;

    private String type;
    private String format;
    private String title;
    private String language;
    private String genre;
    private String length;
    private String date_published;

    /**
     * empty constructor
     * Assigns
     *      type;
     *      format;
     *      title;
     *      main_author;
     *      secondary_authors;
     */
    public Bibliographic_entry(String type){


        String[] input_print ={"format","title","language","main author","genre","length","date_published"};
        General_imp B_Estringconstruct = new General_imp();
        String[] ans_array = B_Estringconstruct.input(input_print);
        setTable_id(null);
        this.type = type;
        this.format = ans_array[0];
        this.title = ans_array[1];
        this.language = ans_array[2];
        this.authorsList.add(new Author(ans_array[3]));
        this.genre = ans_array[4];
        this.length = ans_array[5];
        this.date_published = ans_array[6];
        set_array("secondary_author");
    }

    public void set_array(String type) {
            // Calls >> setatribs >> setter
            while (true){
                General_imp construct = new General_imp();
                construct.print_type(type);
                String ans = construct.input();
                if (ans.equals("EXIT"))
                    break;
                setatribs(ans, type);
            }
        }

    /**
     * Empty constructor
     */
    public Bibliographic_entry(){

    }

    public void print_atrib() {
        String atribs = toStringcustom();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }

    @Override
    public String toString() {
        return "Bibliographic_entry{" +
                "authorsList=" + authorsList +
                ", table_id=" + table_id +
                ", type='" + type + '\'' +
                ", format='" + format + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", genre='" + genre + '\'' +
                ", length='" + length + '\'' +
                ", date_published='" + date_published + '\'' +
                '}';
    }

    /**
     * Overrides the print location in memory method
     * Use split(",") to print in lines
     */
    public String toStringcustom() {
        String a ="";
        String b = "Bibliographic_entry{" +
                "; type='" + type + '\'' +
                "; format='" + format + '\'' +
                "; title='" + title + '\'' +
                "; language='" + language + '\'' +
                "; genre='" + genre + '\'' +
                "; length='" + length + '\'' +
                "; date_published='" + date_published + '\'';

        for (int count = 0;count < this.authorsList.size();count++)
            a.concat("; authors='" + this.authorsList.get(count).toStringcustom() + '\'');

        return b.concat(a) + '}';

    }
    public String toJSON() {
        return "{" +
        "\"authorsList\"" +":\""+ authorsList +"\""+ ","+
        "\"table_id\"" +":"+ table_id + ","+
                "\"type\"" +":\""+ type   +"\""+ ","+
                "\"format\"" +":\""+ format  +"\"" + ","+
                "\"title\"" +":\""+ title   +"\""+ ","+
                "\"language\"" +":\""+ language  +"\"" + ","+
                "\"genre\"" +":\""+ genre  +"\""+ ","+
                "\"length\"" +":\""+ length + "\""+ ","+
                "\"date_published\"" +":\""+ date_published +"\""
                +"}";
    }

    public void setType(String type) {
        this.type = type;}
    public void setFormat(String format) {
        this.format = format;}
    public void setTitle(String title) {
        this.title = title;}
    public void setLanguage(String language) {
        this.language = language;}
    public void setMain_author(String main_author) {
        this.authorsList.set(0, new Author(main_author));}
    public void setGenre(String genre) {
        this.genre = genre;}
    public void setLength(String length) {
        this.length = length;}
    public void setDate_published(String date_published) {
        this.date_published = date_published;}

    /**
     * Simple iter to add secondary authors
     * Examples: ilustrator
     * @param author
     */
    public void setSecondary_authors(String author) {

            System.out.println(authorsList);


            String atrib = "secondary_author";
        General_imp construct = new General_imp();
            int ans = construct.print_mod_array(atrib);
            if (ans >= 0 && ans < this.authorsList.size())
                this.authorsList.set(ans,new Author(author));
                // TODO Remove the object from Repo  check Authors table
            Author temp_author = new Author(author);
            authorsList.add(temp_author);
        }

    /**
     * Sets the new value for the atribute passed as arg
     * @param atrib new value of the atrib
     * @param type type of atribute to change
     */
    public void setatribs(String atrib, String type) {
        if (type.toLowerCase().contains("genre"))
            setGenre(atrib);
        else if (type.toLowerCase().contains("length"))
            setLength(atrib);
        else if (type.toLowerCase().contains("format"))
            setFormat(atrib);
        else if (type.toLowerCase().contains("date_published"))
            setDate_published(atrib);
        else if (type.toLowerCase().contains("title"))
            setTitle(atrib);
        else if (type.toLowerCase().contains("main"))
            setMain_author(atrib);
        else if (type.toLowerCase().contains("language"))
            setLanguage(atrib);
        else if (type.toLowerCase().contains("secondary_author"))
            setSecondary_authors(atrib);
        else
            System.out.println("No change made in parent's attributes");
    }

    public void setTable_id(Long table_id) {
        this.table_id = table_id;
    }

    public Bibliographic_entry(Author main_author, List<Author> secondary_authorsList,
                               Long table_id, String type, String format,
                               String title, String language, String genre,
                               String length, String date_published) {
        this.authorsList.add(main_author);
        for(Author count : secondary_authorsList)
            this.authorsList.add(count);
        this.table_id = table_id;
        this.type = type;
        this.format = format;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.length = length;
        this.date_published = date_published;
    }

    public Author getMain_author() {
        return authorsList.get(0);
    }

    public List<Author> getSecondary_authorsList() {
        return authorsList;
    }

    public Long getTable_id() {
        return table_id;
    }
} // END class bibliographic entry
