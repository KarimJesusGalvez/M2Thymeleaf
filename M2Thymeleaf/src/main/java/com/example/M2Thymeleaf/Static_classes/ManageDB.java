package com.example.M2Thymeleaf.Static_classes;

import com.example.M2Thymeleaf.Bibliographic_classes.*;
import com.example.M2Thymeleaf.Implementations.General_imp;
import com.example.M2Thymeleaf.Repos.Author_Repo;
import com.example.M2Thymeleaf.Repos.B_E_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Operates with the database
 * All methods are static, the class shouldn't be instantiated
 *
 */
@Service //Extends @Component
public class ManageDB{

    @Autowired
    B_E_Repo b_e_repo;
    @Autowired
    Author_Repo author_repo;


    public ManageDB() {
    }


    public ManageDB(B_E_Repo b_e_repo, Author_Repo author_repo) {
        this.b_e_repo = b_e_repo;
        this.author_repo = author_repo;
    }

    /**
     * Adds another registry to the com.projectM2.Database.DB
     * @return registries to added to the BD in Bibliographic format
     */
    public Bibliographic_entry add_registry(){

        // TODO almost 50 lines; break into smaller pieces
        // TODO maybe call(); ?

        General_imp construct = new General_imp();
        construct.printtype();
        String type_array = construct.input();

        if(type_array.equalsIgnoreCase("book")
                || type_array.charAt(0) == '2'
                || type_array.toLowerCase().charAt(0) == 'b'){
            Bibliographic_entry newbook = new B_E_Book();
            for (Author author: newbook.getSecondary_authorsList())
            author_repo.save(author);
            b_e_repo.save(newbook);
            return newbook;
        }
        else if (type_array.equalsIgnoreCase("Audiobook")
                ||type_array.charAt(0) == '3'
                || type_array.toLowerCase().charAt(0) == 'a'){
            Bibliographic_entry newaudiobook = new B_E_Audiobook();
            for (Author author: newaudiobook.getSecondary_authorsList())
                author_repo.save(author);
            b_e_repo.save(newaudiobook);
            return newaudiobook;
        }

        else if (type_array.equalsIgnoreCase("Movie")
                || type_array.charAt(0) == '4'
                || type_array.toLowerCase().charAt(0) == 'm'){
            Bibliographic_entry newMovie = new B_E_Movie();
            for (Author author: newMovie.getSecondary_authorsList())
                author_repo.save(author);
            b_e_repo.save(newMovie);
            return newMovie;
        }
        else if (type_array.charAt(0) == '5' // Recorded music
                || type_array.toLowerCase().charAt(0) == 'r'){
            Bibliographic_entry newrecordedaudio = new B_E_Recorded_Music ();
            for (Author author: newrecordedaudio.getSecondary_authorsList())
                author_repo.save(author);
            b_e_repo.save(newrecordedaudio);
            return newrecordedaudio;
        }
        else if (type_array.charAt(0) == '6' // Music sheet
                || type_array.toLowerCase().charAt(0) == 'n'){
            Bibliographic_entry newmusicsheet = new B_E_Music_Sheet();
            for (Author author: newmusicsheet.getSecondary_authorsList())
                author_repo.save(author);
            b_e_repo.save(newmusicsheet);
            return newmusicsheet;
        }

        else if (type_array.charAt(0) == '7' // Webpage
                || type_array.toLowerCase().charAt(0) == 'w'){
            Bibliographic_entry newebpage = new B_E_Webpage();
            for (Author author: newebpage.getSecondary_authorsList())
                author_repo.save(author);
            b_e_repo.save(newebpage);
            return newebpage;
        }


        if (type_array.charAt(0) == '-' // TODO Add new media type
                && type_array.charAt(1) == '1'){

           // TODO Pending implementation
            Bibliographic_entry emptyB_E = new Bibliographic_entry("Undefined");
            //bibliographic_entry_repo.save(emptyB_E);
            return emptyB_E;
        }
        else if (type_array.charAt(0) == '1') {
            Bibliographic_entry emptyB_E = new Bibliographic_entry("Undefined");
            for (Author author: emptyB_E.getSecondary_authorsList())
                author_repo.save(author);
            b_e_repo.save(emptyB_E);
            return emptyB_E;
        }
        else {
            System.out.println("UNKNOWN data type");
            try{
                throw new InputError();
            }
            catch( InputError error ) {
                error.printStackTrace();
                System.out.println("Adding an empty object");
                Bibliographic_entry emptyB_E = new Bibliographic_entry("temp");
                b_e_repo.save(emptyB_E);
                return emptyB_E;
            }
        }

    }


    /**
     *Access an atribute of the Objects in the com.projectM2.Database.DB
     * through the search method then replaces it with the values provided
     * if The atribute is capable of expanding the value can be simply added
     * @return The updated Registry list
     */
    public  List<Bibliographic_entry> change_registry(List<Bibliographic_entry> registries){

        List<Integer> index = Search_registry.search(registries);
        if (index.size() > 0){

            int changeindex = -1;
            for (Integer count : index) {

                registries.get(count).print_atrib();
                System.out.println("Do you want to change this registry?");
                System.out.println("(Y/N)");
                System.out.println("=====");
                General_imp input_obj = new General_imp();
                String confirmation = input_obj.input();
                boolean type_confirm = evaluate(confirmation);
                if (type_confirm == true) {
                    changeindex = count;
                    break;
                }
            }

            // 2nd OPTION GET && SET // for each one Not comfortable at all!

            String[] atrib_array = registries.get(changeindex).toStringcustom().split(";");
            for (int count = 2; count < atrib_array.length; count++) {
                // count is 2 to omit the name of the class and the type
                System.out.println("Do you want to change" + atrib_array[count]);
                System.out.println("(Y/N)");
                System.out.println("=====");
                General_imp input_obj = new General_imp();
                String confirmation = input_obj.input();
                boolean type_confirm = evaluate(confirmation);
                if (type_confirm == true) {

                    System.out.println("Enter the new value");
                    General_imp input_obj2 = new General_imp();
                    String value_change = input_obj2.input();

                    registries.get(changeindex).setatribs(value_change, atrib_array[count]);

                    break; // TODO Not final, ask if you want to change something else?
                }

            }



            // 1st OPTION Prints, removes then adds // Temporary solution

            /*
             registries.remove(index);
             System.out.println("Entry removed.");
             System.out.println("Rewrite the entry from scratch");
             registries.add(com.projectM2.Static_classes.ManageDB.add_registry());
             */

            // 3rd OPTION
            // TODO Deep search the index where the match was found
            // TODO and bring up that setter exclusively

            /*
            //int deepsearch = registries.get(index).toString().lastIndexOf(type_search);
            //int deepsearch = Staticclasses.Search_registry.search(registries.get(index).toString());

            com.projectM2.Implementations.General_imp input_obj = new com.projectM2.Implementations.General_imp();
            System.out.println("What do you want to change");
            String type_search =  input_obj.Interfaces.input();
             System.out.println("Insert the new value");
             String type_change =  input_obj.Interfaces.input();
            if (type_search.contains("ISBN"))
                registries.get(index).setISBN(type_change);
             }
            System.out.println(" ");

             */
        }
        return registries;
    }

    /**
     *
     * @return the master list of registries after removal
     * of the specified item if no match is found in any field
     * the list is returned without change
     */
    public List<Bibliographic_entry> erase_registry(List<Bibliographic_entry> registries) {

        List<Integer> index = Search_registry.search(registries);
        if (index.size() > 0){
            System.out.println("Initializing deleting procedure of"); // to obj.print_atrib()

            String confirmation = "N"; // To delete Y/N
            int deleteindex = -1; // final index to process
            boolean delete_confirm = false; // Confirm to boolean
            try {
                for (Integer count : index) {
                    registries.get(count).print_atrib();
                    System.out.println("(Y/N)");
                    System.out.println("=====");
                    General_imp input_obj = new General_imp();
                    confirmation = input_obj.input();
                    delete_confirm = evaluate(confirmation);
                    if (delete_confirm)
                        deleteindex = count;
                    else
                        throw new InputError();
                }

                Bibliographic_entry item_removed = registries.remove(deleteindex);
                b_e_repo.deleteById(item_removed.getTable_id());
                item_removed.print_atrib();
                System.out.println(" deleted");
                System.out.println(" ");
            }
            catch (InputError error){
                error.printStackTrace();
                //pass, back to the menu
            }
        }
        return registries;
    }

    /**
     * Marks the com.projectM2.Database.DB for substitution in main
     * with database1 = new database; database1.startup()
     *
     * @return boolean to confirm choice
     */
    public  boolean delete_all() {
        System.out.println("Initializing deleting procedure...");
        System.out.println("Are you absolutely sure you want ");
        System.out.print("to ERASE the DATABASE?");
        System.out.println("(Y/N)");
        System.out.println("=====");
        General_imp input_obj = new General_imp();
        String confirmation = input_obj.input();

        //String confirmation = deleteall().Interfaces.input();

        boolean delete = evaluate(confirmation);
        if (delete == true) {
            return true;
        } else {
            return false;
        }
    }
        /**
         * Checks if the Interfaces.input has returned
         * a valid confirmation
         *
         * @param option to validate (should be passed
         *               from the Interfaces.input method)
         * @return True is option is an affirmative response
         *  false if the option is negative or not relevant
         */
        private boolean evaluate (String option){

            try {

                if ((option.toLowerCase().charAt(0) == 'y')
                        || (option.equals("1"))) {
                    return true;

                } else if((option.toLowerCase().charAt(0) == 'n')
                        || (option.equals("0"))) {
                    //pass
                } else {
                    throw new InputError();
                }
            } catch (Exception e) {
                System.out.println("Wrong Interfaces.input");

            }
            return false;
        }

    public void print_repo() {System.out.println(b_e_repo.findAll());}
} //END Class
