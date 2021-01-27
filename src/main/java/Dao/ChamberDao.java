package Dao;

import Model.Chamber;
import Util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChamberDao {
    EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
    Scanner t = new Scanner(System.in);

    public void createChamber() {
        Chamber c = new Chamber();
        System.out.println("Introduzca el numero de la cámara");
        c.setId(t.nextInt());
        System.out.println("Introduzca la temperatura que soporta la cámara");
        c.setMaxtemp(t.nextInt());
        System.out.println("Introduzca el sensor de la cámara");
        c.setSensor1(t.nextInt());
        System.out.println("Introduzca otro sensor de la cámara");
        c.setSensor2(t.nextInt());
        System.out.println("Puerta cerrada=0, Puerta abierta= 1");
        c.setPuerta(t.nextBoolean());
        System.out.println("Motor apagado=0, Motor encendido=1");
        c.setMotor(t.nextBoolean());
        entity.getTransaction().begin();
        entity.persist(c);
        entity.getTransaction().commit();
        System.out.println("Cámara creada");
    }

    public void createChamber(int id, int tem_max, int s1, int s2, boolean puerta, boolean motor) {
        Chamber c = new Chamber(id, tem_max, s1, s2, puerta, motor);
        entity.getTransaction().begin();
        entity.persist(c);
        entity.getTransaction().commit();
        System.out.println("Cámara creada");
    }
    public void updateChamber() {
        System.out.println("Indica que nº de cámara quieres modificar");
        int id = t.nextInt();

        Chamber c = entity.find(Chamber.class, id);

        if (c != null) {

            System.out.println("Cámara nº +" + c.getId() + " encontrada");
            System.out.println("Introduzca la temperatura que soporta la cámara");
            c.setMaxtemp(t.nextInt());
            System.out.println("Introduzca el sensor de la cámara");
            c.setSensor1(t.nextInt());
            System.out.println("Introduzca otro sensor de la cámara");
            c.setSensor2(t.nextInt());
            System.out.println("Puerta cerrada=0, Puerta abierta= 1");
            c.setPuerta(t.nextBoolean());
            System.out.println("Motor apagado=0, Motor encendido=1");
            c.setMotor(t.nextBoolean());
            entity.getTransaction().begin();
            entity.merge(c);
            entity.getTransaction().commit();
            System.out.println("Cámara actualizada");

        }
    }

        public void updateChamber(int id, int max_t, int s1, int s2, boolean puerta, boolean motor) {

            Chamber c = entity.find(Chamber.class, id);

            if (c != null) {

                System.out.println("Cámara nº +" + c.getId() + " encontrada");
                c.setMaxtemp(max_t);
                c.setSensor1(s1);
                c.setSensor2(s2);
                c.setPuerta(puerta);
                c.setMotor(motor);
                entity.getTransaction().begin();
                entity.merge(c);
                entity.getTransaction().commit();
                System.out.println("Cámara actualizada");

            }
        }

    public Chamber findById(int id) {

        Chamber chamber = entity.find(Chamber.class, id);

        System.out.println("Cámara nº "+chamber.getId()+" encontrada" );
        return chamber;
    }

    public List<Chamber> getAllChamber() {
        List<Chamber> chambers = new ArrayList<>();
        Query query = entity.createQuery("SELECT c FROM Chamber c");
        chambers = query.getResultList();
        for (Chamber c : chambers) {
            System.out.println(c);
        }
        return chambers;


    }

    public void removeChamber (int id){
        Chamber c = new Chamber();

        c = entity.find(Chamber.class, id);
        if (c != null) {
            entity.getTransaction().begin();
            entity.remove(c);
            entity.getTransaction().commit();
            System.out.println("Cámara eliminada...");
        } else {
            System.out.println("Producto no encontrado...");
        }


    }
    public void removeChamber (){
        System.out.println("Introduce la id de la cámara que se desea borrar");
        int id=t.nextInt();
        Chamber c = new Chamber();

        c = entity.find(Chamber.class, id);
        if (c != null) {
            entity.getTransaction().begin();
            entity.remove(c);
            entity.getTransaction().commit();
            System.out.println("Cámara eliminada...");
        } else {
            System.out.println("Producto no encontrado...");
        }


    }


}
