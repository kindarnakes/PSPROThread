package Controller;

import Dao.ChamberDao;
import Model.Log;
import Util.JPAUtil;

public class LogController extends Thread{

    private boolean run;
    private Log log;


    private LogController() {
    }

    public LogController(Log log) {
        this.log = log;
        this.run = true;
    }

    public void shutdown(){
        run = false;
        this.interrupt();
    }

    @Override
    public void run() {
        super.run();
        ChamberDao chamberDao = new ChamberDao();
        chamberDao.getAllChamber().forEach(c ->{
            this.log.saveLog(c);
        });
        this.run = true;
        while(this.run){
            try {
                Thread.sleep(30000);
                chamberDao.getAllChamber().forEach(c ->{
                    this.log.saveLog(c);
                });
            } catch (InterruptedException e) {
                if(this.run) {
                    e.printStackTrace();
                    this.run = false;
                }
            }
        }

    }
}
