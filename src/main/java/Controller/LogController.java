package Controller;

import Model.Chamber;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class LogController extends Thread{

    private boolean run;
    private static final String LOGFILE = "registro.log";

    public boolean saveLog(Chamber c){
        Boolean saved = false;

        try(
            FileWriter out = new FileWriter(LOGFILE, true);
            PrintWriter writer = new PrintWriter(out, true);
            ){


            writer.append("----------------------------------------------------------------------------\n" +
                    "Número de cámara: " + c.getId() +  "\tDía: " + LocalDate.now() +"\tHora: " + LocalTime.now().toString()+
                    "\nTemperatura máxima permitida: " + c.getMaxtemp() +
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

    @Override
    public void run() {
        super.run();
        this.run = true;
        while(this.run){
            try {
                Thread.sleep(30000);
                Chamber c = new Chamber(1, 30, ThreadLocalRandom.current().nextInt(-20,40), ThreadLocalRandom.current().nextInt(-20,40),
                        ThreadLocalRandom.current().nextBoolean(), ThreadLocalRandom.current().nextBoolean());
                this.saveLog(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.run = false;
            }
        }

    }
}
