package Server;

import Server.Controller.MainController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        MainController thread = null;


        try{
            server = new ServerSocket(55000);


            while(true) {
                socket = server.accept();
                thread = new MainController(socket);
                thread.run();

            }



        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
