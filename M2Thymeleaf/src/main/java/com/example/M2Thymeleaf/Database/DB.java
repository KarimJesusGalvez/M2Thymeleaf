package com.example.M2Thymeleaf.Database;

import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Implementations.General_imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The central class, includes the main registry
 * and the console menu to access these
 */
@Component
public class DB {


    @Autowired
    public Menu query1;

    List<Bibliographic_entry> registries = new ArrayList<>();
    private String creation_date = LocalDateTime.now().toString();

    //public Menu query1 = new Menu(registries);


    public DB(Menu query1) {
        this.query1 = query1;
    }

    public DB() {
    }

    /**
     * pass the virtual registries in menu to the DB
     */
    public void update_reg() {
        this.registries = query1.update();
    }


    /**
     * Compares every line in the file with every string in the DB
     * @param path of the copy
     * @return true if all of the strings are copied false if not
     */
    public boolean check_copy(String path) {

        File BDtxt = new File(path);


        try {
            BufferedReader DBfile = new BufferedReader(new FileReader(BDtxt));

            String filestring = DBfile.readLine(); // Line copy

            List<Boolean> Boolean_array= new ArrayList<>();


            while (filestring != null) { // till the end of the file
                for (Bibliographic_entry count : registries) {

                    String[] split_array = count.toStringcustom().split(";");
                    for (String check : split_array) {
                        if (check.contains(filestring)) // .isequalignorecase()
                           Boolean_array.add(true);

                    }
                }
                System.out.println("checking filestring...: " + filestring + " \\ OK \\");
                filestring = DBfile.readLine();

            }
            DBfile.close();
            boolean final_check = false;
            for (int a = 0; a < Boolean_array.size();a++)
                if (Boolean_array.get(a))
                    final_check = true;
            if (final_check)
                System.out.println("Final check Done..." );
                return true;
        }
         catch (FileNotFoundException ex) {
             System.out.println("File not found, rewrite path");
            ex.printStackTrace(System.out);
            return false;
        } catch (IOException ex) {
            System.out.println("Access denied?");
            System.out.println("Close other programs and/or ");
            System.out.print("use another path");
            ex.printStackTrace(System.out);
            return false;
        } finally {
            // DBfile.close();
        }
    }

    /**
     * Writes the String data in the database(List registries) into
     * a txt file in the specified path param
     * @param path | The absolute system specific path to create the file
     * @return String | the complete path to the actual file created,
     * it is just a simple way to confirm the validity
     * of the path in the rest of methods
     */
    public String write_txt(String path){

        // TODO append to existing file ??
        File BDtxt = new File(path);

        try {
            PrintWriter Writeobj = new PrintWriter(BDtxt);
            /*
            Writeobj.println("File created");
            Writeobj.append('/');
            Writeobj.println(" ");

             */
            for (Bibliographic_entry count : registries){
                String[] temp1 =count.toStringcustom().split(";");
                int a = 0;
                for ( a = 0; a < temp1.length;a++) {

                    Writeobj.println(temp1[a]);
                    if (a == temp1.length-1)
                        Writeobj.println("/======/\n");
                }
            }

            Writeobj.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }
        System.out.println("Writing Done...");
        return BDtxt.getAbsolutePath();
    }

    /**
     * Initializes the copy and verifies it
     * @return boolean | To allow the main to terminate or not
     */
    public boolean close(){

        String path = setpath();
        path = write_txt(path);
        if(check_copy(path))
            return true;
        else {
            System.out.println("Corruption in data");
            System.out.println("Reinitializing... ");
            System.out.println("Please copy the data manually");
            return false;
        }

    }

    /**
     * Asks through input the desired path to create a file
     * @return String | containing the System-valid path to be used
     */
    public String setpath(){

        String path = "C:\\data\\TestDB.txt";
        try {
            General_imp genconstruct = new General_imp();
            System.out.println("Insert a valid system path to save the data");
            System.out.println("Remember \\ equals \\\\");
            path = genconstruct.input();
            if (path.isEmpty())
                throw new BadAttributeValueExpException("Empty Value");
            File BDtxt = new File(path); // try to access the path
        }
        catch(BadAttributeValueExpException error){
            error.printStackTrace();
            path = "C:\\data\\TestDB.txt";
            System.out.println("empty value, path is set to: " + path);

        }
        catch(Exception error){
            path = "C:\\data\\TestDB.txt";
            System.out.println("Non defined error, path is set to " + path);

        }
        return path;
    }

    public String getCreation_date() {
        return creation_date;
    }
} // END of Class
