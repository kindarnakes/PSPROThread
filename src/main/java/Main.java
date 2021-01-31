import Controller.LogController;
import Dao.ChamberDao;
import GUI.GUI;
import Model.Log;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
        //Evitamos que salga el log de Hibernate apagando los logs
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(java.util.logging.Level.OFF);

        Log log = Log.getINSTANCE();
        LogController logController = new LogController(log);
        logController.start();
        GUI.startGUI();
        logController.shutdown();

    }
}
