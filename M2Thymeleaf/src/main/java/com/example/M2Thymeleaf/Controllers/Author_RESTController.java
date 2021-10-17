package com.example.M2Thymeleaf.Controllers;

import com.example.M2Thymeleaf.Bibliographic_classes.Author;
import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;
import com.example.M2Thymeleaf.Repos.Author_Repo;
import com.example.M2Thymeleaf.Repos.B_E_Repo;
import com.example.M2Thymeleaf.Static_classes.ManageDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class provides simple CRUD operations on the JPA repositories
    Create
    find all
    fing by id
    mod
    del
    del all
    count
    check exists by id

 *Methods should always return a JSON object or a list of JSON objects
*/
@RestController
public class Author_RESTController {

    @Autowired
    General_imp construct;
    @Autowired
    ManageDB managedb;

    List<Bibliographic_entry> authoristries;
    private Author_Repo author_repo;

    public Author_RESTController(List<Bibliographic_entry> authoristries, B_E_Repo b_e_repo, Author_Repo author_repo) {
        System.out.println(" REST Controller created");
        this.authoristries = authoristries;
        this.author_repo = author_repo;
    }

    @PostMapping("/rest/author/add")
    public Author create(@RequestBody Author author){

            //authoristries.toString();
            author.setAuthor_id(null);
        author_repo.save(author);
        return  author; 
        // return "redirect: /rest/author/all"
    }
    
    @GetMapping("/rest/author/all/string")
    public String[] findAllString(){
        return author_repo.findAll().toString().split(","); // To String en el HTML
    }

    @GetMapping("/rest/author/{id}")
    public Author get_ById(@PathVariable Long id){

        if (author_repo.getById(id) != null)
            return author_repo.getById(id);
        else
            return author_repo.getById(1L);
        // TODO FIX get_ById method
    }


    @PutMapping("/rest/author/mod")
    public Author modify_ById(@RequestBody Author author){
        try {
            author_repo.save(author);
            //check copy
            return get_ById(author.getAuthor_id());

        }catch(NullPointerException error ){
            System.out.println("Id not found");
            error.printStackTrace();
            return author;
        }
        catch(Exception error ){
        System.out.println("Unknown error");
        error.printStackTrace();
        return author;
      }


    }
    @PutMapping("/rest/author/mod/{id}/{value}/{type}")
    public void modify_ById(@PathVariable Long id,@PathVariable String value,@PathVariable String type){
        try {
            Author temp = author_repo.getById(id);
            temp.setatribs(value,type);
        }catch(Exception error ){
            System.out.println("Id not found");
            error.printStackTrace();
        }
    }
    @PutMapping("/author/update")
    public void update(@RequestBody Author author){
        // Deprecated
        if (author.getAuthor_id() != null && author_repo.existsById(author.getAuthor_id()))
            author_repo.save(author);
    }

    @DeleteMapping("/rest/author/del/{id}")
    public void delete_ById(@PathVariable Long id){
        try {
            author_repo.getById(id);
            author_repo.deleteById(id);
        }catch(Exception error ){
            System.out.println("Id not found try again");
            error.printStackTrace();
        }
    }
    @DeleteMapping("/rest/author/del")
    public void delete_ById(@RequestBody Bibliographic_entry bibliographic_entry){
        try {
            author_repo.deleteById(bibliographic_entry.getTable_id());
        }catch(Exception error ){
            System.out.println("Object does not exists try /rest/author/add");
            error.printStackTrace();
        }
    }
    @DeleteMapping("/rest/author/del/all")
    public void delete_AllConsole(){
        if (managedb.delete_all())
            author_repo.deleteAll();
    }
    @DeleteMapping("/rest/author/del/all/y")
    public void delete_All(){
            author_repo.deleteAll();
            /*
            for (Bibliographic_entry count : b_e_repo)
                b_e_repo.deleteById(count.getTable_id());
             */
    }
    @GetMapping("/rest/author/count")
    public String countauthor(){
        long count = author_repo.count();
        return "The number of elements is " + count;
        // TODO add count according provided param
    }
    @GetMapping("/rest/author/{type}/count")
    public String countyear(@PathVariable String year){
        int count =  author_repo.findByBirthyear(year).size();
        return "The number of elements is " + count;

    }
    
    @GetMapping("/rest/author/evaluate/{id}/{value}")
    public boolean evaluateauthor(@PathVariable Long id,@PathVariable String value){
        try {
            if (author_repo.getById(id).toStringcustom().toLowerCase().contains(value.toLowerCase()))
                return true;
        }catch(Exception error) {
            error.printStackTrace();

        }
        return false;
    }


    // Test methods


    @GetMapping("/test/authors/add")
    public void demoData(){

        List<Author> sec_autors = new ArrayList<>();
        sec_autors.add(new Author("Secondary example"));
        author_repo.save(sec_autors.get(0));
        sec_autors.add(new Author("3rd example"));
        author_repo.save(sec_autors.get(1));
   

        List<Author> sec_autors2 = new ArrayList<>();
        sec_autors.add(new Author("4red example"));
        author_repo.save(sec_autors.get(0));
        sec_autors.add(new Author("5th"));
        author_repo.save(sec_autors.get(1));
       
    }
    @GetMapping("/test/authors/add2")
    public void demoAuthorData(){

        List<Author> sec_autors = new ArrayList<>();
        sec_autors.add(new Author("Secondary example"));
        author_repo.save(sec_autors.get(0));
        sec_autors.add(new Author("3rd example"));
        author_repo.save(sec_autors.get(1));
        author_repo.save(new Author("Eugenia Perez Martinez"));

        author_repo.save(new Author("Mark Pollack"));

    }

    @GetMapping("/test/authors/string")
    public String[] findAuthorString() {
        return author_repo.findAll().toString().split(","); // To String en el HTML
    }
    @GetMapping("/test/authors")
    public List<Author> findAuthor() {
        // TODO  fix ERROR in JSON parsing
        return author_repo.findAll();
    }

} // END of Class

