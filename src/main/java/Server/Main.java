package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;


        try{
            server = new ServerSocket(55000);


            while(true) {
                socket = server.accept();
            }



        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
