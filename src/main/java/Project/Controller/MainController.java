package Project.Controller;

import Project.DAO.ChamberDao;
import Project.Model.Arranque;
import Project.Model.Chamber;
import Project.Model.ClientType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainController extends Thread {
    Socket connection;
    ObjectInputStream in;
    ObjectOutputStream out;
    ChamberDao chamberDao;

    public MainController(Socket connection) throws IOException {
        this.connection = connection;
        this.in = new ObjectInputStream(connection.getInputStream());
        this.out = new ObjectOutputStream(connection.getOutputStream());
        this.chamberDao = new ChamberDao();
    }


    @Override
    public void run() {
        super.run();
        try {
            Object o = in.readObject();
            System.out.println(o);
            if (o instanceof ClientType) {
                switch ((ClientType) o) {
                    case Temperatura:
                        temperatura();
                        break;
                    case Puerta:
                        puerta();
                        break;
                    case Administracion:
                        administracion();
                        break;
                }
            }
            in.close();
            out.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void temperatura() throws IOException {
        Integer id = in.readInt();//recibe id
        Integer sensor = in.readInt(); //recibe sensor 1 o 2
        Integer value = in.readInt(); //recibe valor
        chamberDao = new ChamberDao(chamberDao.findById(id));
        System.out.println(chamberDao.getId());
        boolean updated = false;
        if (chamberDao.getId() == id) {
                if (sensor == 1) {
                    chamberDao.setSensor1(value); //recibe valor si sensor 1
                } else if (sensor == 2) {
                    chamberDao.setSensor2(value); //recibe valor si sensor 2
                }
                updated = chamberDao.updateChamber();
                if (updated) {
                    new Arranque(chamberDao).start();
                }
        }
        System.out.println(updated);
        out.writeBoolean(updated); //envia booleano para saber si actualizo
        out.flush();
    }


    public void puerta() throws IOException {
        Integer id = in.readInt();//recibe id
        chamberDao = new ChamberDao(chamberDao.findById(id));
        boolean updated = false;

        System.out.println(chamberDao.getId());
        if (chamberDao.getId() == id) {
                chamberDao.setPuerta(in.readBoolean()); //recibe valor puerta
                updated = chamberDao.updateChamber();
                if (updated) {
                    new Arranque(chamberDao).start();
                }
        }
        System.out.println(updated);
        out.writeBoolean(updated); //envia booleano para saber si actualizo
        out.flush();
    }

    public void administracion() throws IOException, ClassNotFoundException {
        Integer opt = in.readInt(); //recibimos opcion
        ChamberDao chamberDao;
        Chamber c;
        boolean done = false;
        switch (opt){
            case 1:
                out.writeBoolean(true); //aceptamos opcion
                Integer id = in.readInt();
                chamberDao = new ChamberDao();
                c = chamberDao.findById(id);
                out.writeObject(c); //enviamos chamber
                out.flush();
                break;
            case 2:
                out.writeBoolean(true); //aceptamos opcion
                Object o = in.readObject(); //recibimos chamber
                if(o instanceof Chamber){
                    c = (Chamber) o;
                    chamberDao = new ChamberDao(c);
                    done = chamberDao.updateChamber();
                    out.writeBoolean(done); //decimos si esta o no creada
                    out.flush();
                    new Arranque(chamberDao).start();
                 }
                break;
            default:
                out.writeBoolean(false); //negamos opcion
        }

    }
}
