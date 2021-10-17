package com.example.M2Thymeleaf.Controllers;

import com.example.M2Thymeleaf.Bibliographic_classes.Author;
import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;
import com.example.M2Thymeleaf.Repos.Author_Repo;
import com.example.M2Thymeleaf.Repos.B_E_Repo;
import com.example.M2Thymeleaf.Static_classes.ManageDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

 *Methods always return a JSON object or a list of JSON objects
*/
@RestController
public class Menu_RESTController {

    @Autowired
    General_imp construct;
    @Autowired
    ManageDB managedb;

    List<Bibliographic_entry> registries;
    private B_E_Repo b_e_repo;
    private Author_Repo author_repo;

    public Menu_RESTController(List<Bibliographic_entry> registries,B_E_Repo b_e_repo,Author_Repo author_repo) {
        System.out.println("Has creado REST Controller");
        this.registries = registries;
        this.b_e_repo = b_e_repo;
        this.author_repo = author_repo;
    }

    // TODO Search by all
    // TODO Delete @Body
    // TODO del one
    // TODO .count()



    @PostMapping("/rest/reg/add")
    public Bibliographic_entry create(@RequestBody Bibliographic_entry bibliographic_entry){

            //registries.toString();
            bibliographic_entry.setTable_id(null);
        b_e_repo.save(bibliographic_entry);
        return  bibliographic_entry; // To String in HTML ??
        // return "redirect: /rest/reg/all"
    }


    @GetMapping("/rest/reg/all")
    public List<Bibliographic_entry> findAll(){
        return b_e_repo.findAll(); // To String en el HTML
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
    public Bibliographic_entry post_retrievebyid(@RequestBody Long id){
        Bibliographic_entry temp =b_e_repo.getById(id);
        return temp;
    }



    @PutMapping("/rest/reg/mod/{id}")
    public Bibliographic_entry modify_ById(@PathVariable Long id){
        Bibliographic_entry temp =b_e_repo.getById(id);
        // TODO modify method
        return temp;
    }

    @DeleteMapping("/rest/reg/del/{id}")
    public Bibliographic_entry delete_ById(@PathVariable Long id){
        Bibliographic_entry temp =b_e_repo.getById(id);
        b_e_repo.deleteById(id);
        return temp;
    }

    @GetMapping("/test/add")
    public void demoData(){
        Author eug = new Author("Eugenia Perez");
        author_repo.save(eug);

        List<Author> sec_autors = new ArrayList<>();
        Bibliographic_entry temp1 = new Bibliographic_entry(eug, sec_autors,null,"book","paperback",
                "Spring 5","Spanish",",LAN,Java,Spring ","407pg","2018");
        b_e_repo.save(temp1);
        Bibliographic_entry temp2 = new Bibliographic_entry(eug, sec_autors,null,"book","paperback",
                "Spring 5","Spanish",",LAN,Java,Spring ","407pg","2018");
        b_e_repo.save(temp2);
    }

    @PutMapping("/reg/update")
    public void update(@RequestBody Bibliographic_entry b_entry){
        if (b_entry.getTable_id() != null && b_e_repo.existsById(b_entry.getTable_id()))
            b_e_repo.save(b_entry);
    }


}

