package Project.Model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Log {


    private static final String LOGFILE = "registro.log";
    private static final Log INSTANCE = new Log();
    private File f;

    public static Log getINSTANCE() {
        return INSTANCE;
    }

    private Log() {
        this.f = new File(LOGFILE);
    }

    public synchronized boolean saveLog(Chamber c){
        Boolean saved = false;

        try(
                FileWriter out = new FileWriter(this.f, true);
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
        notifyAll();

        return saved;
    }

    public synchronized Optional<String> loadLog(){
        String log = null;

        try(
                FileReader in = new FileReader(this.f);
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

        notifyAll();
        return Optional.ofNullable(log);
    }

}
