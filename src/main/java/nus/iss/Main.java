package nus.iss;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Cookie c = new Cookie();




    }

    public void checkDirectory(String directoryPath){
        File newDirectory = new File(directoryPath);
        if (newDirectory.exists()){
            System.out.println("Directory already exists!");
        } else {
            System.out.println("Created the directory!");
            newDirectory.mkdir();
        }
    }
}