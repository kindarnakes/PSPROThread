package Dao;

import Model.Chamber;
import Util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChamberDao extends Chamber {
    Scanner t = new Scanner(System.in);
    EntityManager manager;

    public ChamberDao(int id, int maxtemp, int sensor1, int sensor2, boolean puerta, boolean motor, Scanner t) {
        super(id, maxtemp, sensor1, sensor2, puerta, motor);

    }

    public ChamberDao(int id, int maxtemp, int sensor1, int sensor2) {
        super(id, maxtemp, sensor1, sensor2);
    }

    public boolean createChamber(int id) {
        boolean result = false;
        EntityManager manager = null;
        try {
            Chamber c = new Chamber(id, this.getMaxtemp(), this.getSensor1(), this.getSensor2());
            manager = JPAUtil.getManager();
            manager.getTransaction().begin();
            if (c != null) {
                manager.persist(c);
            }
            manager.getTransaction().commit();
            manager.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                manager.close();
            }
        }
        return result;
    }


        public boolean  updateChamber (){
            boolean result = false;
            EntityManager manager = null;
            try {
                Chamber chamber = new Chamber(this.getId(),this.getMaxtemp(), this.getSensor1(), this.getSensor2());
                chamber.setId((this.getId()));
                manager = JPAUtil.getManager();
                manager.getTransaction().begin();
                if (chamber != null) {
                    manager.merge(chamber);
                    result = true;
                }

                manager.getTransaction().commit();
                manager.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    manager.close();
                }
            }

            return result;
        }

        public Chamber findById (int id){
            EntityManager manager = null;
            Chamber chamber = new Chamber();
            try {
                manager = JPAUtil.getManager();
                chamber = manager.find(Chamber.class, id);
                manager.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    manager.close();
                }
            }
            return chamber;
        }

        public static List<Chamber> getAllChamber () {
            List<Chamber> chambers = new ArrayList<>();
            EntityManager manager = null;
            try {
                manager = JPAUtil.getManager();
                chambers = manager.createQuery("FROM Chamber").getResultList();
                manager.clear();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    manager.close();
                }
            }
            return chambers;
        }

        public boolean removeChamber ( int id){
            boolean result = false;
            EntityManager manager = null;
            try {
                Chamber c = new Chamber();
                manager = JPAUtil.getManager();
                manager.getTransaction().begin();

                if (c != null) {
                    c = manager.find(Chamber.class, id);
                    manager.remove(c);
                    result = true;
                }
                manager.getTransaction().commit();
                manager.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    manager.close();
                }
            }

            return result;
        }
    }






