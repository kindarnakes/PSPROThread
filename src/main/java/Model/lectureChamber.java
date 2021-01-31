package Model;

public class lectureChamber extends Thread {

    //OPCION 2
    Chamber chamber = new Chamber(1, -30, -27, -27, true, false);


    public synchronized void arranque() {
        System.out.println("Leyendo temperatura s1= " + chamber.getSensor1());
        System.out.println("Leyendo temperatura s2= " + chamber.getSensor2());


    }

    public synchronized void sensorTemperatura() {
        if(!sensorPuerta()) {
            if (chamber.getMaxtemp() <= chamber.getSensor1() || chamber.getMaxtemp() <= chamber.getSensor2()) {
                System.out.println("Se enciende motor");
                chamber.setMotor(true);
            } else {
                System.out.println("Se apaga motor");
            }
        }
    }

    public synchronized boolean sensorPuerta() {
        boolean result=false;
        if (chamber.isPuerta()) {
            result= true;
            System.out.println("Puerta abierta");
            System.out.println("Se enciende motor");
            chamber.setMotor(true);


        } else {
            result =false;
            System.out.println("Se apaga motor");
        }
        return result;
    }
}


