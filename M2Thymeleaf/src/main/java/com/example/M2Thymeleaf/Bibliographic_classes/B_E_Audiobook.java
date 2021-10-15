package com.example.M2Thymeleaf.Bibliographic_classes;


import com.example.M2Thymeleaf.Implementations.General_imp;

public class B_E_Audiobook extends B_E_Book {


    private String narrator;

    public B_E_Audiobook() {
        super(); // >> book >> Bibliographic entry
        General_imp construct = new General_imp();
        String[] input_print ={"narrator"};
        String[] ans_array = construct.input(input_print);
        setNarrator(ans_array[0]);

    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }
    public void setatribs(String atrib, String type){
        super.setatribs(atrib,type);
        if (type.toLowerCase().contains("narrator"))
            setNarrator(atrib);
        else
            System.out.println("No change made in audiobook's attributes");
    }
    public void print_atrib() {
        String atribs = toString();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }

    @Override
    public String toString() {
        return "B_E_Audiobook{" + super.toString() +
                "; narrator='" + narrator + '\'' +
                "} ";
    }
}
