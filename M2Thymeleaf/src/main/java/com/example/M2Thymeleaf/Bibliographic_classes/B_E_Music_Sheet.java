package com.example.M2Thymeleaf.Bibliographic_classes;

import com.example.M2Thymeleaf.Implementations.General_imp;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class B_E_Music_Sheet extends B_E_Book{

    private String movements;
    @OneToMany
    private List<String> instrumentation = new ArrayList<String>();

    public B_E_Music_Sheet() {
        super();

        General_imp construct = new General_imp();
        String[] input_print ={"movements"};
        String[] ans_array = construct.input(input_print);
        this.movements = ans_array[0];
        set_array("instrument");
        }

    @Override
    public String toStringcustom() {
        return "B_E_Music_Sheet{" + super.toStringcustom() +
                "; movements='" + movements + '\'' +
                "; instrumentation=" + instrumentation +
                "} " ;
    }

    @Override
    public String toString() {
        return "B_E_Music_Sheet{" +
                "movements='" + movements + '\'' +
                ", instrumentation=" + instrumentation +
                "} " + super.toString();
    }

    public void print_atrib() {
        String atribs = this.toStringcustom();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }

    public void setatribs(String atrib, String type){
        super.setatribs(atrib,type);
        if (type.toLowerCase().contains("movements"))
            setMovements(atrib);
        else if (type.toLowerCase().contains("instrument"))
            setInstrumentation(atrib);
        else
            System.out.println("No change made in music sheet's attributes");
    }

    public void setInstrumentation(String instrument) {

        System.out.println(this.instrumentation);
        General_imp gen_construct = new General_imp();

        String atrib = "instrument";
        int ans = gen_construct.print_mod_array(atrib);
        if (ans >= 0 && ans < this.instrumentation.size())
            this.instrumentation.remove(ans);
        this.instrumentation.add(instrument);

    }
    public void setMovements(String movements) {
        this.movements = movements;
    }
    public void set_array(String type){
        // Calls >> setatribs >> setter
        while (true){
            General_imp construct = new General_imp();

            construct.print_type(type);
            String ans = construct.input();
            if (ans.equals("EXIT")) {
                break;
            }
            setatribs(ans, type);
        }
    }
} // END of class
