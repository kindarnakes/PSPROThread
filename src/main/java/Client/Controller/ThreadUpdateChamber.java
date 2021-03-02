package Client.Controller;

import Client.Dao.ChamberDao;
import Client.Model.Arranque;

public class ThreadUpdateChamber extends Thread {

    private ChamberDao chamberDao;

    public ThreadUpdateChamber(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        chamberDao.setSensor1(-27);
        chamberDao.setSensor2(-27);
        chamberDao.updateChamber();
        Arranque arranque = new Arranque(chamberDao);
        arranque.start();
    }
}
