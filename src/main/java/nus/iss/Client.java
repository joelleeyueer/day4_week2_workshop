package nus.iss;

import java.io.*;
import java.net.Socket;

public class Client {

    private static int port = 3000;

    public Client(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        clientStart();
    }

    public static void clientStart() throws IOException {
        Socket client = new Socket("localhost",port) ;
        System.out.println("Client established, listening in on port " + port);

        while (true){
            try{
                OutputStream os = client.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                Console cons = System.console();
                String line = cons.readLine("Type get-cookie to get a cookie, or close to close the connection!");
                dos.writeUTF(line);
                dos.flush();

                InputStream is = client.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);

                String readServerInput=dis.readUTF();
                System.out.println(readServerInput);

            } catch (Exception e){
                client.close();
                System.err.println(e);
            }
            client.close();
        }


    }
}
