package Controller;

import Dao.ChamberDao;
import Model.Arranque;

public class ThreadLectureChamber extends Thread {

    private ChamberDao chamberDao;

    public ThreadLectureChamber(ChamberDao chamberDao) {
        this.chamberDao = chamberDao;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("----------------------------------------------");
        System.out.println("Cámara="+chamberDao.getId());
        System.out.println("Máxima temperatura="+chamberDao.getMaxtemp());
        System.out.println("Sensor 1="+chamberDao.getSensor1());
        System.out.println("Sensor 2="+chamberDao.isPuerta());
        System.out.println("Puerta="+chamberDao.isMotor());

    }



}
