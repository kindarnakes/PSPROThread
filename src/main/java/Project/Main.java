package Project;

import Project.Controller.MainController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        //Evitamos que salga el log de Hibernate apagando los logs
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

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
