
package Client.Controller;

import Client.Dao.ChamberDao;
import Client.Model.Arranque;


public class ThreadUpdate1 extends Thread{
    
     private ChamberDao chamberDao;

    public ThreadUpdate1(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        chamberDao.setSensor1(-34);
        chamberDao.updateChamber();
        Arranque arranque = new Arranque(chamberDao);
        arranque.start();
    }
}
