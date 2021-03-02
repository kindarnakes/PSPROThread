package Project.Controller;

import Project.DAO.ChamberDao;
import Project.Model.Chamber;

import java.io.*;
import java.net.Socket;

public class MainController extends Thread{
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
            Chamber o = (Chamber) in.readObject();
            System.out.println(o);
            /*Object o = in.readObject();
            System.out.println(o);
            if(o instanceof ClientType){
                switch ((ClientType) o){
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
            }*/
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
        do{
            Boolean updated = false;
            if(sensor == 1){
                chamberDao.setSensor1(in.readInt()); //recibe valor si sensor 1
                updated = chamberDao.updateChamber();
            }else if(sensor == 2){
                chamberDao.setSensor2(in.readInt()); //recibe valor si sensor 2
                updated = chamberDao.updateChamber();
            }
            out.writeObject(updated); //envia booleano para saber si actualizo
            next = in.readUTF(); //recibe texto y o n
        }while(next.matches("y")); //continua bucle si recibio y
    }


    public void puerta(){

    }

    public void administracion(){

    }
}
