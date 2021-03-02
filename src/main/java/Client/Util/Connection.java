package Client.Util;

import Client.Model.ClientType;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Connection {

    Socket cliente = null;
    BufferedReader entrada = null;
    DataOutputStream salida = null;
    String ipServidor = "localhost";

    public void conectar() {
        try {
            cliente = new Socket(ipServidor, 55000);
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new DataOutputStream(cliente.getOutputStream());
        } catch (
                UnknownHostException var9) {
            System.err.println("El servidor no está levantado");
        } catch (Exception var10) {
            System.err.println("Error: " + var10);
        }
    }

        public void peticiones(ClientType peticion, ObjectOutputStream objeto){
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


