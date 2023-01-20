package nus.iss;

import nus.iss.Cookie;

import java.io.*;
import java.net.Socket;

public class CookieClientHandler implements Runnable{

    private Socket clientSocket;

    public CookieClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        InputStream is = null;
        OutputStream os = null;
            try{
                while (true) {
                    is = clientSocket.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    DataInputStream dis = new DataInputStream(bis);
                    String messageReceived = "";
                    messageReceived = dis.readUTF();

                    os = clientSocket.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    DataOutputStream dos = new DataOutputStream(bos);

                    switch (messageReceived) {
                        case ("close"):
                            System.out.println("Shutting down the server, goodbye!");
                            clientSocket.close();
                            is.close();
                            bis.close();
                            dis.close();
                            os.close();
                            bos.close();
                            dos.close();

                        case ("get-cookie"):
                            System.out.println("Get cookie received");
                            Cookie c = new Cookie();
                            c.readCookieFile();
                            String cookieValue = c.returnCookie();
                            System.out.println(cookieValue);
                            dos.writeUTF(cookieValue);
                            dos.flush();
                    }
                }


            } catch (Exception e){
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.err.println(e);
            } finally {
                try{
                    if (is != null) {
                        is.close();
                    }

                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e){
                    System.err.println(e);
                }

            }
    }
}
