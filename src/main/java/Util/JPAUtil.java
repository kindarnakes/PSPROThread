package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

        private static JPAUtil INSTANCE = new JPAUtil();
        private static final String PERSISTENCE_UNIT = "MariaDB";
        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        private  EntityManager manager = null;

    private JPAUtil() {
    }

    public static JPAUtil getINSTANCE() {
        return INSTANCE;
    }

    public synchronized EntityManager getManager() {
        if(manager != null && manager.isOpen()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            try {
                if (manager == null || !manager.isOpen()) {
                    manager = emf.createEntityManager();
                }
            }catch (IllegalStateException ex){
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            }
            return manager;
        }

        public synchronized void close(){
            manager.close();
            notifyAll();
        }

    }

