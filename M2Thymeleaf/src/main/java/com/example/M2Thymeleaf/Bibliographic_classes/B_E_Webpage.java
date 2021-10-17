package com.example.M2Thymeleaf.Bibliographic_classes;


import com.example.M2Thymeleaf.Implementations.General_imp;

public class B_E_Webpage extends Bibliographic_entry{

    String URL;
    String date_of_retrieval;

    public B_E_Webpage() {
        super("webpage");

        General_imp construct = new General_imp();
        String[] input_print ={"URL","date_of_retrieval"};
        String[] ans_array = construct.input(input_print);
        this.URL= ans_array[0];
        this.date_of_retrieval = ans_array[1] ;

    }

    public void setURL(String URL) {
        this.URL = URL;}
    public void setDate_of_retrival(String date_of_retrival) {
        this.date_of_retrieval = date_of_retrival;}

    public void setatribs(String atrib, String type){
        super.setatribs(atrib,type);
        if (type.toLowerCase().contains("url"))
            setURL(atrib);
        else if (type.toLowerCase().contains("retrival"))
            this.setDate_of_retrival(atrib);
        else
            System.out.println("No change made in webpage's attributes");
    }
    @Override
    public String toStringcustom() {
        return "B_E_Webpage{"  + super.toStringcustom() +
                "; URL='" + URL + '\'' +
                "; date_of_retrival='" + date_of_retrieval + '\'' +
                "} ";
    }

    @Override
    public String toString() {
        return "B_E_Webpage{" +
                "URL='" + URL + '\'' +
                ", date_of_retrieval='" + date_of_retrieval + '\'' +
                "} " + super.toString();
    }

    public void print_atrib() {
        String atribs = toStringcustom();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }

} // END of Class
