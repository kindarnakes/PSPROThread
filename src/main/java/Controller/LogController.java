package Controller;

import Dao.ChamberDao;
import Model.Chamber;
import Model.Log;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

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
        ChamberDao.getAllChamber().forEach(c ->{
            this.log.saveLog(c);
        });
        this.run = true;
        while(this.run){
            try {
                Thread.sleep(30000);
                ChamberDao.getAllChamber().forEach(c ->{
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
