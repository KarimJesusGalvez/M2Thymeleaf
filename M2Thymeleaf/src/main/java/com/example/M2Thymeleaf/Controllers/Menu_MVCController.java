package com.example.M2Thymeleaf.Controllers;

import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;
import com.example.M2Thymeleaf.Static_classes.ManageDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Menu_MVCController {

    @Autowired
    General_imp construct;
    @Autowired
    ManageDB managedb;

    List<Bibliographic_entry> registries;

    public Menu_MVCController(List<Bibliographic_entry> registries){
        System.out.println("Has creado MVC Controller");
        this.registries = registries;}

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
}
