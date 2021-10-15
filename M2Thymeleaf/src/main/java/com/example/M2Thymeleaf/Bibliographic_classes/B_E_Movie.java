package com.example.M2Thymeleaf.Bibliographic_classes;

import com.example.M2Thymeleaf.Implementations.General_imp;

import java.util.ArrayList;
import java.util.List;

public class B_E_Movie extends Bibliographic_entry {

    private String resolution;
    private List<String> other_lang_audio = new ArrayList<String>();
    private List<String> other_lang_subtitles = new ArrayList<String>();
    private List<String> actors = new ArrayList<String>();
    private String id_PCA_number;

    public B_E_Movie() {
        super("movie");

        General_imp construct = new General_imp();
        String[] input_print ={"resolution","id_PCA_number"};
        String[] ans_array = construct.input(input_print);
        this.resolution= ans_array[0];
        this.id_PCA_number = ans_array[1] ;
        set_array("lang_audio");
        set_array("lang_sub");
        set_array("actor");

    }
    public void print_atrib() {
        String atribs = toString();
        String[] temp_array = atribs.split(";");
        for (int count = 0; count < temp_array.length;count++){
            System.out.println(temp_array[count]);
        }
    }


    public void setResolution(String resolution) {
        this.resolution = resolution;}

    public void setOther_lang_audio(String audio) {

        General_imp construct_gen = new General_imp();

            System.out.println(other_lang_audio);
            General_imp gen_construct = new General_imp();

            String atrib = "lang_audio";
            int ans = gen_construct.print_mod_array(atrib);
            if (ans >= 0 && ans < this.other_lang_audio.size())
                this.other_lang_audio.remove(ans);
            this.other_lang_audio.add(audio);

    }
    public void setOther_lang_subtitles(String sub) {

        General_imp construct_gen = new General_imp();

            System.out.println(other_lang_subtitles);
            General_imp gen_construct = new General_imp();

            String atrib = "lang_subtitle";
            int ans = gen_construct.print_mod_array(atrib);
            if (ans >= 0 && ans < this.other_lang_subtitles.size())
                this.other_lang_subtitles.remove(ans);
            this.other_lang_subtitles.add(sub);
        }

    public void setActors(String name) {

        General_imp construct_gen = new General_imp();

        System.out.println(actors);
        General_imp gen_construct = new General_imp();

        String atrib = "actor";
        int ans = gen_construct.print_mod_array(atrib);
        if (ans >= 0 && ans < this.actors.size())
            this.actors.remove(ans);
        this.actors.add(name);
    }

    public void setId_PCA_number(String id_PCA_number) {
        this.id_PCA_number = id_PCA_number;
    }

    @Override
    public String toString() {
        return "B_E_Movie{" + super.toString() +
                "; resolution='" + resolution + '\'' +
                "; other_lang_audio=" + other_lang_audio +
                "; other_lang_subtitles=" + other_lang_subtitles +
                "; actors=" + actors.toString() +
                "; id_PCA_number='" + id_PCA_number + '\'' +
                "} ";
    }

    public void setatribs(String atrib, String type){
        super.setatribs(atrib,type);
       if (type.toLowerCase().contains("resolution"))
           setResolution(atrib);
       else if (type.toLowerCase().contains("lang_audio"))
           setOther_lang_audio(atrib);
       else if (type.toLowerCase().contains("lang_sub"))
           setOther_lang_subtitles(atrib);
       else if (type.toLowerCase().contains("actor"))
           setActors(atrib);
       else if (type.toLowerCase().contains("pca_number"))
           setId_PCA_number(atrib);
       else
           System.out.println("No change made in movie's attributes");
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

    // To be used in testing
    public B_E_Movie(String type) {
        super(type);
    }

    public B_E_Movie( String resolution, List<String> other_lang_audio, List<String> other_lang_subtitles, List<String> actors, String id_PCA_number) {

        this.resolution = resolution;
        this.other_lang_audio = other_lang_audio;
        this.other_lang_subtitles = other_lang_subtitles;
        this.actors = actors;
        this.id_PCA_number = id_PCA_number;
    }
} // END of class