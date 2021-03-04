package Project.Controller;

import Project.DAO.ChamberDao;
import Project.Model.Arranque;
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
        String next;
        Integer id = in.readInt();//recibe id
        Integer sensor = in.readInt(); //recibe sensor 1 o 2
        chamberDao = new ChamberDao(chamberDao.findById(id));
        if (chamberDao.getId() == id) {
            do {
                Boolean updated = false;
                if (sensor == 1) {
                    chamberDao.setSensor1(in.readInt()); //recibe valor si sensor 1
                } else if (sensor == 2) {
                    chamberDao.setSensor2(in.readInt()); //recibe valor si sensor 2
                }
                updated = chamberDao.updateChamber();
                out.writeBoolean(updated); //envia booleano para saber si actualizo
                next = in.readUTF(); //recibe texto y o n
                if (updated) {
                    new Arranque(chamberDao).start();
                }
            } while (next.matches("y")); //continua bucle si recibio y}
        }
    }


    public void puerta() throws IOException {
        String next;
        Integer id = in.readInt();//recibe id
        chamberDao = new ChamberDao(chamberDao.findById(id));
        if (chamberDao.getId() == id) {
            do {
                Boolean updated = false;
                chamberDao.setPuerta(in.readBoolean()); //recibe valor puerta
                updated = chamberDao.updateChamber();
                out.writeBoolean(updated); //envia booleano para saber si actualizo
                next = in.readUTF(); //recibe texto y o n
                if (updated) {
                    new Arranque(chamberDao).start();
                }
            } while (next.matches("y")); //continua bucle si recibio y}
        }
    }

    public void administracion() {

    }
}
