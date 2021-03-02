package Client;

import Client.Controller.LogController;
import Client.Dao.ChamberDao;
import Client.GUI.GUI;
import Client.Model.Chamber;
import Client.Model.Log;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) throws IOException {

     Socket cliente = null;
     BufferedReader entrada = null;
     ObjectOutputStream salida = null;
     String ipServidor = "localhost";

     GUI.startGUI();

      try {
       cliente = new Socket(ipServidor, 55000);
       //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
       salida = new ObjectOutputStream(cliente.getOutputStream());
      } catch (
              UnknownHostException var9) {
       System.err.println("El servidor no está levantado");
      } catch (Exception var10) {
       System.err.println("Error: " + var10);
      }

      salida.writeObject(2);


     }




}
