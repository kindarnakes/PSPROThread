package Server.DAO;

import Server.Model.Chamber;
import Server.Util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class ChamberDao extends Chamber {
    private EntityManager manager;
    private JPAUtil util;

    public ChamberDao(int id, int maxtemp, int sensor1, int sensor2, boolean puerta, boolean motor) {
        super(id, maxtemp, sensor1, sensor2, puerta, motor);
        util = JPAUtil.getINSTANCE();

    }

    public ChamberDao(int id, int maxtemp, int sensor1, int sensor2) {
        super(id, maxtemp, sensor1, sensor2);
        util = JPAUtil.getINSTANCE();
    }

    public ChamberDao(Chamber chamber){
        super(chamber.getId(), chamber.getMaxtemp(), chamber.getSensor1(), chamber.getSensor2(), chamber.isPuerta(), chamber.isMotor());
        util = JPAUtil.getINSTANCE();
    }

    public ChamberDao() {
        util = JPAUtil.getINSTANCE();
    }

    public synchronized boolean createChamber(int id) {
        boolean result = false;
        try {
            Chamber c = new Chamber(id, this.getMaxtemp(), this.getSensor1(), this.getSensor2());
            manager = util.getManager();
            manager.getTransaction().begin();
            if (c != null) {
                manager.persist(c);
            }
            manager.getTransaction().commit();
            util.close();
        } catch (PersistenceException ex) {
            if (manager != null) {
                util.close();
            }
        }
        notifyAll();
        return result;
    }


        public synchronized boolean  updateChamber (){
            boolean result = false;
            try {
                Chamber chamber = new Chamber(this.getId(),this.getMaxtemp(), this.getSensor1(), this.getSensor2(), this.isPuerta(), this.isMotor());
                chamber.setId((this.getId()));
                manager = util.getManager();
                manager.getTransaction().begin();
                if (chamber != null) {
                    manager.merge(chamber);
                    result = true;
                }

                manager.getTransaction().commit();
                util.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    util.close();
                }
            }
            notifyAll();

            return result;
        }

        public synchronized Chamber findById (int id){
            Chamber chamber = new Chamber();
            try {
                manager = util.getManager();
                chamber = manager.find(Chamber.class, id);
                util.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    util.close();
                }
            }
            notifyAll();
            return chamber;
        }

        public synchronized List<Chamber> getAllChamber () {
            List<Chamber> chambers = new ArrayList<>();
            try {
                manager = util.getManager();
                chambers = manager.createQuery("FROM camaras").getResultList();
                util.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    util.close();
                }
            }
            notifyAll();
            return chambers;
        }

        public synchronized boolean removeChamber ( int id){
            boolean result = false;
            try {
                Chamber c = new Chamber();
                manager = util.getManager();
                manager.getTransaction().begin();

                if (c != null) {
                    c = manager.find(Chamber.class, id);
                    manager.remove(c);
                    result = true;
                }
                manager.getTransaction().commit();
                util.close();
            } catch (PersistenceException ex) {
                if (manager != null) {
                    util.close();
                }
            }

            notifyAll();
            return result;
        }
    }






