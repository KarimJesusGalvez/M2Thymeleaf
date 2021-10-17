package com.example.M2Thymeleaf.Controllers;

import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;
import com.example.M2Thymeleaf.Repos.B_E_Repo;
import com.example.M2Thymeleaf.Static_classes.ManageDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class B_E_MVCController {

    @Autowired
    General_imp construct;
    @Autowired
    ManageDB managedb;

    private List<Bibliographic_entry> registries;
    private B_E_Repo b_e_repo;
    public B_E_MVCController(List<Bibliographic_entry> registries, B_E_Repo b_e_repo){
        System.out.println("Has creado MVC Controller");
        this.registries = registries;
        this.b_e_repo = b_e_repo;
    }

    @GetMapping("/print/menu")
    public String printmenu(){
        // construct.print();
    return "index";}

    @GetMapping("/print/menu/type")
    public void printtype(){construct.printtype();}

    @GetMapping("/reg/add")
    public String addregistry(){registries.add(managedb.add_registry());

        return "Addtype";}

    @GetMapping("/reg/mod")
    public void modregistry(){managedb.change_registry(registries);}

    @GetMapping("/reg/erase")
    public void deleteById(@PathVariable Long id){
        managedb.erase_registry(registries);

    if (b_e_repo.existsById(id))
        b_e_repo.deleteById(id);

}
    @GetMapping("/reg/print")
    public String findAll(Model model){

        model.addAttribute("b_e_repo_list",registries);
        return"b_e_repo_list";
    }
    /*
    @GetMapping("/reg/mod/print")
    public String showForm(Model model){
        //TODO
    model.addAttribute("product", new product());
    // model is a placeholder you need an object
    return"/products/product-form";
    }
    @PostMapping
    public String update(){}

    @GetMapping("/reg/print")
    public String create(@ModelAttribute("product") Product product){
        //TODO model

        return"redirect:/products";
    }
    
     */
}

