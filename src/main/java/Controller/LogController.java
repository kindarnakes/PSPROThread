package Controller;

import Dao.ChamberDao;
import Model.Chamber;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class LogController extends Thread{

    private boolean run;
    private static final String LOGFILE = "registro.log";
    private static final LogController LOG = new LogController();

    private LogController() {
    }

    public static LogController getLOG() {
        return LOG;
    }

    public void shutdown(){
        run = false;
        this.interrupt();
    }

    public synchronized boolean saveLog(Chamber c){
        Boolean saved = false;

        try(
            FileWriter out = new FileWriter(LOGFILE, true);
            PrintWriter writer = new PrintWriter(out, true);
            ){


            writer.append("----------------------------------------------------------------------------\n" +
                    "Número de cámara: " + c.getId() +  "\tDía: " + LocalDate.now() +"\tHora: " + LocalTime.now().toString()+
                    "\nTemperatura máxima permitida: " + c.getMaxtemp() + "ºC" +
                    "\nValor del sensor de temperatura: 1: " + c.getSensor1() + "ºC 2: " + c.getSensor2() + "ºC" +
                    "\nEstado del motor: " + (c.isMotor()?"encendido": "apagado") +
                    "\nEstado de la puerta: " + (c.isPuerta()?"abierta":"cerrada")+
                    "\n----------------------------------------------------------------------------\n");
            saved = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return saved;
    }

    public synchronized Optional<String> loadLog(){
        String log = null;

        try(
                FileReader in = new FileReader(LOGFILE);
                BufferedReader br = new BufferedReader(in);
        ){
            String file = "";
            while(br.ready()) {
                file += br.readLine() + "\n";
            }
            log = file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(log);
    }

    @Override
    public void run() {
        super.run();
        ChamberDao.getAllChamber().forEach(c ->{
            this.saveLog(c);
        });
        this.run = true;
        while(this.run){
            try {
                Thread.sleep(30000);
                ChamberDao.getAllChamber().forEach(c ->{
                    this.saveLog(c);
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
