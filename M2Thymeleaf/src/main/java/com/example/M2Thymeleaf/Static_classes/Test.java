package com.example.M2Thymeleaf.Static_classes;

import com.example.M2Thymeleaf.Bibliographic_classes.Author;
import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Repos.Author_Repo;
import com.example.M2Thymeleaf.Repos.B_E_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Test {

    @Autowired
    B_E_Repo B_ERepository;
    //public void B_E_RepoController(B_E_Repo repo) {this.B_ERepository = repo;}
    @Autowired
    Author_Repo author_repo;
    //public void AuthorController(Author_Repo repo) {this.author_repo = repo;}

    public Test() {}

    public static void main(String[] args) {


        String testvar = testerror();
        System.out.println(testvar);
        List<String> arraytest = new ArrayList<String>();
        arraytest.add("Joseph");
        arraytest.add("EXIT");
        arraytest.toString();
        System.out.println(arraytest.toString());
        System.out.println(arraytest);

        String edition = "3rd";
        String date_published = "1978";
        String id_ISBN = "978-67-463-44";
        String id_LC = "5959996";
        String a = ("Bibliographicclasses.B_E_book{" + '\'' +
                ", edition='" + edition + '\'' +
                ", date_published='" + date_published + '\'' +
                ", id_ISBN='" + id_ISBN + '\'' +
                ", id_LC='" + id_LC + '\'' +
                "} ");

        System.out.println("a is: "+a);
        System.out.println("index ed: "+a.indexOf("edition"));
        System.out.println("last ed: "+a.lastIndexOf("w"));
        System.out.println("index date "+a.indexOf("date_published"));
        String f = a.substring(0,10);
        String g = a.substring(10,20);
        g.indexOf("aa");
        System.out.println("lasted: "+a.lastIndexOf("edition"));
        int indexa = a.indexOf("date_published");
        int lastindexa = a.lastIndexOf("date_published");
        String testsub = a.substring(indexa,lastindexa );
        System.out.println("print testsub: "+testsub);
        System.out.println("fsub is " + f);
        System.out.println("gsub is " + g);
        String[] b = a.split(",");
        for (int z = 0; z < b.length; z++) {
            System.out.println("z is: "+b[z]);
            try {
                Thread.sleep(3242);
            } catch (Exception e) {
                System.out.println("You're not waiting are you?");
            }
        }

    }

    public static String testerror(){
        try{

            System.out.println("try start");

            throw new IndexOutOfBoundsException();
            //return "finaltry";


        }
        catch(IndexOutOfBoundsException error){
            System.out.println("catch1 start");
            error.printStackTrace();
            return "finalcatch1";
        }
        finally {
            System.out.println("finally start");
            return"finalfinally";
        }

    }
    public void nonstatictest(){
        Author eug = new Author("Eugenia Perez");
        author_repo.save(eug);

        List<Author> sec_autors = new ArrayList<>();
        Bibliographic_entry tempsave = new Bibliographic_entry(eug, sec_autors,null,"book","paperback",
                "Spring 5","Spanish",",LAN,Java,Spring ","407pg","2018");
        B_ERepository.save(tempsave);

        Bibliographic_entry temp2 = new Bibliographic_entry("book");
        author_repo.save(temp2.getMain_author());
        for (Author count : temp2.getSecondary_authorsList())
            author_repo.save(count);
        B_ERepository.save(temp2);
    }

/*

    public Bibliographic_entry test_suite() {


        B_E_Movie movietest = new B_E_Movie("Movie");
        movietest.setatribs("English","language");
        movietest.setatribs("Quentin Tarantino","main");
        movietest.setatribs("DVD","format");
        movietest.setatribs("Reservoir Dogs","title");
        System.out.println("==");
        movietest.setatribs("Drama","genre");
        movietest.setatribs("99","length");
        movietest.setatribs("Panavision lenses","resolution");
        movietest.setatribs("1992","date_published");
        movietest.setatribs("English","lang_audio");
        movietest.setatribs("English","lang_sub");
        movietest.setatribs("Harvey Keitel","actor");
        movietest.setatribs("PCA-31489","PCA_number");
        return movietest;

    }

    public Bibliographic_entry test_suite2() {

        List<String> list = new ArrayList<>();
        list.add("examplelist1");
        B_E_Movie movietest = new B_E_Movie("Panavision lenses",
                 list,list, list, "PCA-31489");
        return movietest;

    }

 */
}
