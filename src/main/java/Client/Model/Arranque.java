package Client.Model;

import Client.Dao.ChamberDao;

public class Arranque extends Thread{

    private ChamberDao chamber;

    public Arranque(ChamberDao chamber) {
        this.chamber = chamber;
    }

    @Override
    public void run() {
        super.run();
        if((chamber.getSensor1() >= chamber.getMaxtemp() || chamber.getSensor2() >= chamber.getMaxtemp()) && !chamber.isMotor() && !chamber.isPuerta()){
            chamber.setMotor(true);
            System.out.println("\n\tMotor arrancado id:" + chamber.getId());
        }else if(chamber.isPuerta() && chamber.isMotor()){
            chamber.setMotor(false);
            System.out.println("\n\tMotor parado id:" + chamber.getId());
        }else if(chamber.getSensor1() < chamber.getMaxtemp() && chamber.getSensor2() < chamber.getMaxtemp() && chamber.isMotor()){
            chamber.setMotor(false);
            System.out.println("\n\tMotor parado id:" + chamber.getId());
        }
        chamber.updateChamber();
    }
}
