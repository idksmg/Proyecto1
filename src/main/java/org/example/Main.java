package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> listaDeCadenas = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese ubicación del archivo, ej: ");
        String fpath = in.nextLine();
        if (fpath == "") {
            fpath = "C:\\codigo\\SEMESTRE 3\\Calculator\\datos.txt";
        }

        try {
            File myObj = new File(fpath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listaDeCadenas.add(data);
                myReader.close();

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}