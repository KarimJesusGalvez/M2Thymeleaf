package com.example.M2Thymeleaf.Database;

import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;
import com.example.M2Thymeleaf.Static_classes.ManageDB;
import com.example.M2Thymeleaf.Static_classes.Search_registry;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * UI == front end
 * A simple console Interfaces.input to select options
 */
public class Menu {

    @GetMapping("Index.html")
    public String menuhtml() {
        return "Index.html"; //extension depends on view resolver.
    }

    private List<Bibliographic_entry> registries = new ArrayList<Bibliographic_entry>();


    /**
     * Constructor with 1 param
     * @param registries The table of the database
     */
    public Menu(List<Bibliographic_entry> registries){
        this.registries = registries;
    }

    /**
     * Calls the methods in the appropriate order
     */
    public boolean[] startup(){

        String selection = "temp";

        for (int count = 0; count < 3; count++) {

            General_imp input_obj = new General_imp();
            input_obj.print();
            selection =input_obj.input();


            if (selection.equals("7")
                || selection.toLowerCase().charAt(0) == 's') {
                System.out.println("Closing com.projectM2.Database.Menu");
                System.out.println("...");
                System.out.println("......");
                // count = 99; // unnecessary either this or break
                break;
            }
            else {
                count = 1; // == while
                return call(selection);
            }
        }
        return call(selection);
    }

    /**
     * Passes the menu com.projectM2.Database registries to the actual database
     * @return The updated version of the data after interaction
     * through the menu
     */
    public List<Bibliographic_entry> update(){
        return this.registries;
    }

    /**
     *calls the particular method as specified on the menu
     * @param selection The index of the method to be called
     */
    public boolean[] call(String selection){

        boolean[] call_check = new boolean [8];
        for (int i = 0; i < call_check.length; i++){
            call_check[i] = false;
        }

        if (selection.equals("1" )
                || selection.toUpperCase().charAt(0) == 'P'){
            General_imp printreg = new General_imp();
            printreg.print(registries);
            call_check[0] = true;
        }
        else if (selection.equals("2")
                || selection.toUpperCase().charAt(0) == 'C'){
            try {
                List<Integer> search_index = Search_registry.search(registries);
                for (Integer count : search_index)
                    registries.get(count).print_atrib();
                call_check[1] = true;
            }
            catch (IndexOutOfBoundsException errorIOB){
                System.out.println("No registries");
                errorIOB.printStackTrace();
                call_check[1] = false;
            }
            catch(Exception error2){
                System.out.println("UnknownError in Staticclasses.Search_registry.search()");
                error2.printStackTrace();
                call_check[2] = false; //not necessary
            }

        }
        else if (selection.equals("3")
                || selection.toUpperCase().charAt(0) == 'A'){
            try {
                ManageDB manageDB = new ManageDB();
                this.registries.add(manageDB.add_registry());
                call_check[2] = true;
            }
            catch(Exception generalerror){
                System.out.println("UnknownError in ManageDb.add_registry");
                generalerror.printStackTrace();
                call_check[2] = false; //not necessary
            }

        }
        else if (selection.equals("4")
                || selection.toUpperCase().charAt(0) == 'M'){
            try {
                ManageDB manageDB = new ManageDB();
                this.registries = manageDB.change_registry(registries);
                call_check[3] = true;
            }
            catch(Exception e){
                // TODO More specific handling
                e.printStackTrace();
                call_check[3] = false; // Unnecessary
            }

        }
        else if (selection.equals("5")
                || selection.toUpperCase().charAt(0) == 'E'){
            try {
                ManageDB manageDB = new ManageDB();
                this.registries = manageDB.erase_registry(registries);
                call_check[4] = true;
            }
            catch(Exception e){
                e.printStackTrace();
                call_check[4] = false; // Unnecessary
            }
        }
        else if (selection.equals("6")
                || selection.toUpperCase().charAt(0) == 'D'){
            System.out.println("=====");
            System.out.println("You are attempting to ERASE the DATABASE");
            System.out.println("=====");
            System.out.println("...");
            System.out.println("......");

            /*
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException threadexception){
            }
            */
            ManageDB manageDB = new ManageDB();
            call_check[5] = manageDB.delete_all();
        }
        else if (selection.equals("7")
                || selection.toUpperCase().charAt(0) == 'S'){
            call_check[6] = true;
        }
        else if (selection.equals("-1")
                || selection.toUpperCase().charAt(0) == 'T'){
            call_check[7] = true;
            /*
            Test test1 = new Test();
            registries.add(test1.test_suite());
            for (int a = 0; a < 100; a++)
                registries.add(test1.test_suite2());
*/
        }

        else if (selection.equals("-99")) {
          System.exit(-1);
        }
        else if (selection.equals("548")){
            this.registries.add(new Bibliographic_entry("test"));
        }
        else { // TODO merge this with evaluate (interface?)
            try{
                throw new IllegalAccessException();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("Input failure");
                System.out.println("Provided: " + selection);
                System.out.println("Please Input ");
                System.out.print("the corresponding ");
                System.out.print("Number or Letter");
            }

        }
        return call_check;

    }


    /**
     * Evaluates the option selected
     * if the option is not valid calls startup and Interfaces.input again
     * return should be a String number corresponding to the option in startup()
     *
     * @return The same option argument after validation
     */
    /*
    public String evaluate(String option){
        boolean input_check = false;
        try {
            // TODO Check the if condition for cha <= 8
            if ( (int) option.charAt(0) < 8) {
                input_check = true;
                return option;

            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch (Exception e) {
            System.out.println("Wrong Interfaces.input");
        }
        finally {
            if (!input_check) {
                startup();

            }
        }
        return option; // Could create problems on the long run
    }
    */


    public List<Bibliographic_entry> getRegistries() {
        return registries;
    }

    public void setRegistries(List<Bibliographic_entry> registries) {
        this.registries = registries;
    }


} // END of Class