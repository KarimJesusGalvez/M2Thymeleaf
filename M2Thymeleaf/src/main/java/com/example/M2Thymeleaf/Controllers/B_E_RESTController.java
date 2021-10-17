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
public class B_E_RESTController {

    @Autowired
    General_imp construct;
    @Autowired
    ManageDB managedb;

    List<Bibliographic_entry> registries;
    private B_E_Repo b_e_repo;
    private Author_Repo author_repo;

    public B_E_RESTController(List<Bibliographic_entry> registries, B_E_Repo b_e_repo, Author_Repo author_repo) {
        System.out.println(" REST Controller created");
        this.registries = registries;
        this.b_e_repo = b_e_repo;
        this.author_repo = author_repo;
    }

    @PostMapping("/rest/reg/add")
    public Bibliographic_entry create(@RequestBody Bibliographic_entry bibliographic_entry){

            //registries.toString();
            bibliographic_entry.setTable_id(null);
        b_e_repo.save(bibliographic_entry);
        return  bibliographic_entry; // To String in HTML ??
        // return "redirect: /rest/reg/all"
        /*

         */
    }


    @GetMapping("/rest/reg/all/string")
    public String[] findAllString(){
        return b_e_repo.findAll().toString().split(","); // To String en el HTML
    }
    @GetMapping("/rest/reg/{type}/string")
    public String[] findByTypeString(@PathVariable String type){
        return b_e_repo.findByType(type).toString().split(","); // To String en el HTML
    }
    @GetMapping("/rest/reg/all")
    public String findAll(){

        // TODO ERROR fix in JSON parsing
        //return b_e_repo.findAll(); // To String en el HTML
        return  "["+b_e_repo.findById(1L).get().toJSON()+"]";
    }


    @GetMapping("/rest/reg/{id}")
    public Bibliographic_entry get_ById(@PathVariable Long id){

        if (b_e_repo.getById(id).getTable_id() != null)
            return b_e_repo.getById(id);
        else
            return b_e_repo.getById(1L);
        // TODO FIX get_ById method
    }

    @PostMapping("/rest/reg/id")
    public Bibliographic_entry post_ById(@RequestBody Long id){

        // TODO check proper usage
        Bibliographic_entry temp =b_e_repo.getById(id);
        return temp;
    }

    @PutMapping("/rest/reg/mod")
    public Bibliographic_entry modify_ById(@RequestBody Bibliographic_entry bibliographic_entry){
        try {
            b_e_repo.save(bibliographic_entry);
            //check copy
            return get_ById(bibliographic_entry.getTable_id());

        }catch(NullPointerException error ){
            System.out.println("Id not found");
            error.printStackTrace();
            return bibliographic_entry;
        }
        catch(Exception error ){
        System.out.println("Unknown error");
        error.printStackTrace();
        return bibliographic_entry;
      }


    }
    @PutMapping("/rest/reg/mod/{id}/{value}/{type}")
    public void modify_ById(@PathVariable Long id,@PathVariable String value,@PathVariable String type){
        try {
            Bibliographic_entry temp = b_e_repo.getById(id);
            temp.setatribs(value,type);
        }catch(Exception error ){
            System.out.println("Id not found");
            error.printStackTrace();
        }
    }
    @PutMapping("/reg/update")
    public void update(@RequestBody Bibliographic_entry b_entry){
        // Deprecated
        if (b_entry.getTable_id() != null && b_e_repo.existsById(b_entry.getTable_id()))
            b_e_repo.save(b_entry);
    }

    @DeleteMapping("/rest/reg/del/{id}")
    public void delete_ById(@PathVariable Long id){
        try {
            b_e_repo.getById(id);
            b_e_repo.deleteById(id);
        }catch(Exception error ){
            System.out.println("Id not found try again");
            error.printStackTrace();
        }
    }
    @DeleteMapping("/rest/reg/del")
    public void delete_ById(@RequestBody Bibliographic_entry bibliographic_entry){
        try {
            b_e_repo.deleteById(bibliographic_entry.getTable_id());
        }catch(Exception error ){
            System.out.println("Object does not exists try /rest/reg/add");
            error.printStackTrace();
        }
    }
    @DeleteMapping("/rest/reg/del/all")
    public void delete_AllConsole(){
        if (managedb.delete_all())
            b_e_repo.deleteAll();
    }
    @DeleteMapping("/rest/reg/del/all/y")
    public void delete_All(){
            b_e_repo.deleteAll();
            /*
            for (Bibliographic_entry count : b_e_repo)
                b_e_repo.deleteById(count.getTable_id());
             */
    }
    @GetMapping("/rest/reg/count")
    public String countReg(){
        long count = b_e_repo.count();
        return "The number of elements is " + count;
        // TODO add count according provided param
    }
    @GetMapping("/rest/reg/{type}/count")
    public String counttype(@PathVariable String type){
        int count =  b_e_repo.findByType(type).size();
        return "The number of elements is " + count;

    }

    @GetMapping("/rest/reg/evaluate/{id}/{value}")
    public boolean evaluateReg(@PathVariable Long id,@PathVariable String value){
        try {
            if (b_e_repo.getById(id).toStringcustom().toLowerCase().contains(value.toLowerCase()))
                return true;
        }catch(Exception error) {
            error.printStackTrace();

        }
        return false;
    }


    // Test methods


    @GetMapping("/test/add")
    public void demoData(){

        List<Author> sec_autors = new ArrayList<>();
        sec_autors.add(new Author("Secondary example"));
        author_repo.save(sec_autors.get(0));
        sec_autors.add(new Author("3rd example"));
        author_repo.save(sec_autors.get(1));
        Bibliographic_entry temp1 = new Bibliographic_entry(new Author("Eugenia Perez Martinez"), sec_autors,null,"book","paperback",
                "Spring 5","Spanish","LAN;Java;Spring ","407pg","2018");
        author_repo.save(temp1.getMain_author());
        b_e_repo.save(temp1);

        List<Author> sec_autors2 = new ArrayList<>();
        sec_autors.add(new Author("4red example"));
        author_repo.save(sec_autors.get(0));
        sec_autors.add(new Author("5th"));
        author_repo.save(sec_autors.get(1));
        Bibliographic_entry temp2 = new Bibliographic_entry(new Author("Mark Pollack"), sec_autors2,null,"book","Ebook",
                "Spring Data","English","LAN;Java;Spring ","314pg","2013");
        author_repo.save(temp2.getMain_author());
        b_e_repo.save(temp2);

    }
    @GetMapping("/test/add/author")
    public void demoAuthorData(){

        List<Author> sec_autors = new ArrayList<>();
        sec_autors.add(new Author("Secondary example"));
        author_repo.save(sec_autors.get(0));
        sec_autors.add(new Author("3rd example"));
        author_repo.save(sec_autors.get(1));
        author_repo.save(new Author("Eugenia Perez Martinez"));

        author_repo.save(new Author("Mark Pollack"));

    }

    @GetMapping("/test/author/string")
    public String[] findAuthorString() {
        return author_repo.findAll().toString().split(","); // To String en el HTML
    }
    @GetMapping("/test/author")
    public List<Author> findAuthor() {
        // TODO  fix ERROR in JSON parsing
        return author_repo.findAll();
    }

} // END of Class

