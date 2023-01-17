package nus.iss;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port = 3000;

    public Server(int port) {
        this.port = port;
    }

    public void serverStart(Cookie cookie) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Waiting incoming connection");
        Socket socket = server.accept();
        System.out.println("Server established, listening in on port " + port);
            try {
                while(true){
                    InputStream is = socket.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    DataInputStream dis = new DataInputStream(bis);
                    String messageReceived = "";
                    messageReceived = dis.readUTF();

                    OutputStream os = socket.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    DataOutputStream dos = new DataOutputStream(bos);

                    switch (messageReceived){
                        case ("close"):
                            System.out.println("Shutting down the server, goodbye!");
                            server.close();
                            socket.close();
                            bis.close();
                            bos.close();
                            is.close();
                            os.close();
                        case ("get-cookie"):
                            String cookieValue = cookie.returnCookie();
                            System.out.println(cookieValue);
                            dos.writeUTF(cookieValue);
                            dos.flush();
                    }
                }

            } catch (Exception e){
                server.close();
                socket.close();
                System.err.println(e);
            }
    }

    @Override
    public String toString() {
        return "Server{" +
                "port=" + port +
                '}';
    }
}
