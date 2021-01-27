package Model;

import java.util.Objects;

public class Chamber {

    private int id;
    private int maxtemp;
    private int sensor1;
    private int sensor2;
    private boolean puerta;
    private boolean motor;


    public Chamber() {
    }

    public Chamber(int id, int maxtemp, int sensor1, int sensor2, boolean puerta, boolean motor) {
        this.id = id;
        this.maxtemp = maxtemp;
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
        this.puerta = puerta;
        this.motor = motor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(int maxtemp) {
        this.maxtemp = maxtemp;
    }

    public int getSensor1() {
        return sensor1;
    }

    public void setSensor1(int sensor1) {
        this.sensor1 = sensor1;
    }

    public int getSensor2() {
        return sensor2;
    }

    public void setSensor2(int sensor2) {
        this.sensor2 = sensor2;
    }

    public boolean isPuerta() {
        return puerta;
    }

    public void setPuerta(boolean puerta) {
        this.puerta = puerta;
    }

    public boolean isMotor() {
        return motor;
    }

    public void setMotor(boolean motor) {
        this.motor = motor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamber chamber = (Chamber) o;
        return id == chamber.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chamber{" +
                "id=" + id +
                ", maxtemp=" + maxtemp +
                ", sensor1=" + sensor1 +
                ", sensor2=" + sensor2 +
                ", puerta=" + puerta +
                ", motor=" + motor +
                '}';
    }
}
