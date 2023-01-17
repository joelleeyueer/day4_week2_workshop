package nus.iss;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Cookie c = new Cookie();
        Server s = new Server(3000);
        c.readCookieFile();
        c.showCookies();
        s.serverStart(c);



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