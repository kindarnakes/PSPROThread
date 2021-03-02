package Client.Controller;

import Client.Dao.ChamberDao;
import Client.Model.Arranque;

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
