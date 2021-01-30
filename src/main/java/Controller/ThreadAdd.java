package Controller;

import Dao.ChamberDao;
import Model.Arranque;

public class ThreadAdd extends Thread{

    private ChamberDao chamberDao;

    public ThreadAdd(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        chamberDao.createChamber(chamberDao.getId());
        Arranque arranque = new Arranque(chamberDao);
        arranque.start();
    }
}
