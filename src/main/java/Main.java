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

        //seteamos valores iniciales

        ChamberDao chamberDao = new ChamberDao(1, -30, -25, -26, false, true);
        chamberDao.updateChamber();
        chamberDao = new ChamberDao(2, -35, -40,-36, false, false);
        chamberDao.updateChamber();
        chamberDao = new ChamberDao(3, -15, 0, 1, true, false);
        chamberDao.updateChamber();


        Log log = Log.getINSTANCE();
        LogController logController = new LogController(log);
        logController.start();
        GUI.startGUI();
        logController.shutdown();

    }
}
