package general;

import java.io.IOException;

public class Field {

    public static void main(String[] args){
        IOException a = new IOException();

        System.out.println(a.getClass().getSimpleName());

        if (a instanceof IOException){
            System.out.println("Tu ");
        }
    }
}
