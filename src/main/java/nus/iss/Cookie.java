package nus.iss;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    String dirPath = "./data2";
    String fileName = "cookie.txt";

    List<String> cookieItems;

    public void readCookieFile() throws IOException {
        cookieItems = new ArrayList<>();

        File file = new File(dirPath + File.separator + fileName);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String incomingString;

        while (null != (incomingString = br.readLine())){
            cookieItems.add(incomingString);
        }

        fr.close();
        br.close();
    }

    public String returnCookie(){
        Random random = new Random();

        if (cookieItems != null){
            return cookieItems.get((random.nextInt(cookieItems.size())));
        } else {
            return "returnCookie: No cookies found :(";
        }
    }

    public void showCookies(){
        if (cookieItems == null) {
            System.err.println("showCookies: No cookies found :(");
        } else {
            for (String cookie:cookieItems){
                System.out.println(cookie);
            }
        }
    }
}
