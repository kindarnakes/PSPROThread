package Server.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;


@Entity(name = "camaras")
public class Chamber {

    @Id
    @Column(name = "codigoCamara")
    private int id;
    @Column(name = "tempMaxima")
    private int maxtemp;
    @Column(name = "valorS1")
    private int sensor1;
    @Column(name = "valorS2")
    private int sensor2;
    @Column(name = "puerta")
    private boolean puerta;
    @Column(name = "motor")
    private boolean motor;


    public Chamber() {
        this.puerta=false;
        this.motor=false;
    }

    public Chamber(int id, int maxtemp, int sensor1, int sensor2, boolean puerta, boolean motor) {
        this.id = id;
        this.maxtemp = maxtemp;
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
        this.puerta = puerta;
        this.motor = motor;
    }

    public Chamber(int id, int maxtemp, int sensor1, int sensor2) {
        this.id = id;
        this.maxtemp = maxtemp;
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
    }

    public int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
        notifyAll();
    }

    public int getMaxtemp() {
        return maxtemp;
    }

    public synchronized void setMaxtemp(int maxtemp) {
        this.maxtemp = maxtemp;
        notifyAll();
    }

    public int getSensor1() {
        return sensor1;
    }

    public synchronized void setSensor1(int sensor1) {
        this.sensor1 = sensor1;
        notifyAll();
    }

    public int getSensor2() {
        return sensor2;
    }

    public synchronized void setSensor2(int sensor2) {
        this.sensor2 = sensor2;
        notifyAll();
    }

    public boolean isPuerta() {
        return puerta;
    }

    public synchronized void setPuerta(boolean puerta) {
        this.puerta = puerta;
        notifyAll();
    }

    public boolean isMotor() {
        return motor;
    }

    public synchronized void setMotor(boolean motor) {
        this.motor = motor;
        notifyAll();
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
