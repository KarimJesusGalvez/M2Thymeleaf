package com.example.M2Thymeleaf.Static_classes;

import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;

import java.util.ArrayList;
import java.util.List;

/**
 * See  docs for the search method below
 */
public class Search_registry {

    /**
     * Search the Whole Database for a match with the search term provided
     * Creates a list with all the index of the objects that match
     * @param registries The Database
     * @return the list with the index of the matches found
     */
    public static List<Integer> search (List<Bibliographic_entry> registries){

        System.out.println("Introduce the search term");
        General_imp construct_gen = new General_imp();
        String search_target = construct_gen.input();

        List<String> searcharray = new ArrayList<>();
        List<Integer> index_array = new ArrayList<Integer>();
        boolean search_result = false;

        if (registries.size() > 0) {
            for (Bibliographic_entry count : registries) {
                searcharray.add(count.toString());
            }
            for (int a = 0; a < searcharray.size(); a++) {
                if (searcharray.get(a).toLowerCase().contains(search_target.toLowerCase())) {
                    search_result = true;
                    System.out.println("search_result: " + search_result);
                    index_array.add(a);
                }
            }
            if (index_array.size() > 0 && index_array.size() < 10) {
                System.out.println(index_array.size() + " Registries found\n");
                return index_array;
            }
            else {
                System.out.println("Too many results, refine search term");
            }
        }
        else
            System.out.println("No match found");
        return index_array;
    }


    /**
     *   Same as .IndexOf()  ABSURD IMPLEMENTATION
     */
     /*
    public static int search (String string_array){

        System.out.println("Search with a minimum of 3 characters");
        String search_target = "1";
        while (search_target.length() < 3) {
            com.projectM2.Implementations.General_imp construct_gen = new com.projectM2.Implementations.General_imp();
            search_target = construct_gen.Interfaces.input();
        }

        boolean search_result = false;
        for (int  a = 0; a < string_array.length();a++){
            for (int  b = 0; b < search_target.length();b++) {
                if (string_array.charAt(a) == search_target.charAt(b)
                && string_array.charAt(a+1) == search_target.charAt(b+1)
                        && string_array.charAt(a+2) == search_target.charAt(b+2)){
                    search_result = true;
                    return a;
                }
            }
        }
        return -1;

    }
    public static void print_for_search(){

    }

     */

} // END of class
