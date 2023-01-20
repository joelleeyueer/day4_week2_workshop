package nus.iss;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port = 3000;

    public Server(int port) {
        this.port = port;
    }

    public void serverStart(Cookie cookie) throws IOException {
        ServerSocket server = null;
            try {
                while (true) {
                    server = new ServerSocket(port);
                    server.setReuseAddress(true);
                    System.out.println("Waiting incoming connection");
                    Socket client = server.accept();
                    System.out.println("New client connected");

                    CookieClientHandler cch = new CookieClientHandler(client);
                    new Thread(cch).start();
                }
            } catch (Exception e){
                server.close();
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
