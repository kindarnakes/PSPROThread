package Project;

import Project.Controller.MainController;
import Project.DAO.ChamberDao;

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


        ChamberDao chamberDao = new ChamberDao(1, -30, -25, -26, false, true);
        chamberDao.updateChamber();
        chamberDao = new ChamberDao(2, -35, -40,-36, false, false);
        chamberDao.updateChamber();
        chamberDao = new ChamberDao(3, -15, 0, 1, true, false);
        chamberDao.updateChamber();

        ServerSocket server = null;
        Socket socket = null;
        MainController thread = null;



        try{
            server = new ServerSocket(55000);


            while(true) {
                socket = server.accept();
                thread = new MainController(socket);
                thread.run();
                System.out.println("Se ha conectado un cliente: " + socket.getRemoteSocketAddress());

            }



        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
