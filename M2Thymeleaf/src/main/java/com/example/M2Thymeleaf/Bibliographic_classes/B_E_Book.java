package com.example.M2Thymeleaf.Bibliographic_classes;

import com.example.M2Thymeleaf.Implementations.General_imp;

public class B_E_Book extends Bibliographic_entry {


    private String edition;
    private String id_ISBN;
    private String id_LC;

    public B_E_Book() {
        super("book");

        General_imp construct = new General_imp();
        String[] input_print ={"edition","ISBN","LC Number"};
        String[] ans_array = construct.input(input_print);
        this.edition = ans_array[0];
        this.id_ISBN = ans_array[1];
        this.id_LC = ans_array[2];
    }


    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setId_ISBN(String id_ISBN) {
        this.id_ISBN = id_ISBN;
    }

    public void setId_LC(String id_LC) {
        this.id_LC = id_LC;
    }

    public void setatribs(String atrib,String type){
        super.setatribs(atrib,type);
       if (type.toLowerCase().contains("edition"))
           setEdition(atrib);
       else if (type.toUpperCase().contains("ISBN"))
           setId_ISBN(atrib);
       else if (type.toUpperCase().contains("LC"))
           setId_LC(atrib);
       else
           System.out.println("No change made in book's attributes");
    }

    public void print_atrib() {
        String atribs = toStringcustom();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }
    @Override
    public String toStringcustom() {
        return "B_E_book{" + super.toStringcustom() +
                "; edition='" + edition + '\'' +
                "; id_ISBN='" + id_ISBN + '\'' +
                "; id_LC='" + id_LC + '\'' +
                "} " ;
    }

    @Override
    public String toString() {
        return "B_E_Book{" +
                "edition='" + edition + '\'' +
                ", id_ISBN='" + id_ISBN + '\'' +
                ", id_LC='" + id_LC + '\'' +
                "} " + super.toString();
    }
} // END of class