package Client.Util;

import Client.Model.ClientType;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Connection {



        public void peticiones(ClientType peticion){
            try {

                switch (peticion){


                    case Temperatura:
                        break;


                    case Puerta:
                        break;

                    case Administracion:
                        break;

                }


                salida.close();
                entrada.close();
                cliente.close();
            } catch (UnknownHostException var6) {
                System.err.println("No encuentro el servidor en la dirección" + ipServidor);
            } catch (
                    IOException var7) {
                System.err.println("Error de entrada/salida");
            } catch (Exception var8) {
                System.err.println("Error: " + var8);
            }

        }
    }


