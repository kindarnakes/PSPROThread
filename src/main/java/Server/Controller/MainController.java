package Server.Controller;

import java.io.*;
import java.net.Socket;

public class MainController extends Thread{
    Socket connection;
    ObjectInputStream in;
    ObjectOutputStream out;

    public MainController(Socket connection) throws IOException {
        this.connection = connection;
        this.in = new ObjectInputStream(connection.getInputStream());
        this.out = new ObjectOutputStream(connection.getOutputStream());
    }


    @Override
    public void run() {
        super.run();
        try {
            System.out.println(in.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
