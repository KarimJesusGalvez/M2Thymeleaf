package com.example.M2Thymeleaf.Bibliographic_classes;

import com.example.M2Thymeleaf.Implementations.General_imp;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class B_E_Recorded_Music extends Bibliographic_entry{

    private String number_of_tracks;
    @OneToMany
    private List<String> name_of_tracks = new ArrayList<String>();

    public B_E_Recorded_Music() {
        super("Recorded music/audio");

        General_imp construct = new General_imp();
        String[] input_print = {"number_of_tracks"};
        String[] ans_array = construct.input(input_print);
        this.number_of_tracks = ans_array[0];
        set_array("name_of_tracks");
    }

    public void setNumber_of_tracks(String n_o_track) {
        this.number_of_tracks = n_o_track;}

    public void setName_of_tracks(String name) {

        General_imp construct_gen = new General_imp();

                System.out.println(name_of_tracks);
                General_imp gen_construct = new General_imp();

                String atrib = "name_of_track";
                int ans = gen_construct.print_mod_array(atrib);
                if (ans >= 0 && ans < this.name_of_tracks.size())
                    this.name_of_tracks.remove(ans);
                this.name_of_tracks.add(name);
        }

    public void setatribs(String atrib, String type){
        super.setatribs(atrib,type);
        if (type.toLowerCase().contains("number_of_tracks"))
            setNumber_of_tracks(atrib);
        else if (type.toLowerCase().contains("name_of_tracks"))
            setName_of_tracks(atrib);
        else
            System.out.println("No change made in record's attributes");
    }
    public void set_array(String type){
        // Calls >> setatribs >> setter
        while (true){

            General_imp construct = new General_imp();

            construct.print_type( type);
            String ans = construct.input();
            if (ans.equals("EXIT"))
                break;
            setatribs(ans, type);
        }
    }

    @Override
    public String toStringcustom() {
        return "B_E_Recorded_Music{"  + super.toStringcustom() +
                "; number_of_tracks='" + number_of_tracks + '\'' +
                "; name_of_tracks='" + name_of_tracks + '\'' +
                "} ";
    }

    @Override
    public String toString() {
        return "B_E_Recorded_Music{" +
                "number_of_tracks='" + number_of_tracks + '\'' +
                ", name_of_tracks=" + name_of_tracks +
                "} " + super.toString();
    }

    public void print_atrib() {
        String atribs = toStringcustom();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }



}// END of Class
