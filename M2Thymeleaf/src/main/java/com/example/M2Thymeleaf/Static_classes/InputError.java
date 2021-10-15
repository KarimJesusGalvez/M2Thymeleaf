package com.example.M2Thymeleaf.Static_classes;

import java.util.InputMismatchException;

public class InputError extends Exception{

    public InputError(){

        // Add
        System.out.println("\nInput Error raised\n");
        System.out.println("Check the printed message to fix the error\n");
        try {
            throw new InputMismatchException();
        }
        catch(InputMismatchException error){
            //pass
        }
    }
}
