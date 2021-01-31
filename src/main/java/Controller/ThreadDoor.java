package Controller;

import Dao.ChamberDao;
import Model.Arranque;

public class ThreadDoor extends Thread {

    private ChamberDao chamberDao;

    public ThreadDoor(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        chamberDao.setPuerta(true);
        Arranque arranque = new Arranque(chamberDao);
        arranque.start();
    }
}
