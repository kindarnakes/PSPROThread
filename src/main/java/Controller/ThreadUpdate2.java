
package Controller;

import Dao.ChamberDao;
import Model.Arranque;



public class ThreadUpdate2 extends Thread{
    
     private ChamberDao chamberDao;

    public ThreadUpdate2(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        chamberDao.setSensor2(-36);
        chamberDao.updateChamber();
        Arranque arranque = new Arranque(chamberDao);
        arranque.start();
    }
}
