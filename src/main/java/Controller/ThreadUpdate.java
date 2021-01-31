package Controller;

import Dao.ChamberDao;
import Model.Arranque;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadUpdate extends Thread{

    private ChamberDao chamberDao;

    public ThreadUpdate(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        chamberDao.setSensor1(ThreadLocalRandom.current().nextInt(-40,40));
        chamberDao.setSensor2(ThreadLocalRandom.current().nextInt(-40,40));
        chamberDao.updateChamber();
        Arranque arranque = new Arranque(chamberDao);
        arranque.start();
    }
}
