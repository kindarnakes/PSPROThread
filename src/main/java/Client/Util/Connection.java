package Client.Util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class Connection {

    Socket cliente = null;
    BufferedReader entrada = null;
    DataOutputStream salida = null;
    String ipServidor = "127.0.0.1";

    public void conectar(){
        try {
        cliente = new Socket(ipServidor, 2019);
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        salida = new DataOutputStream(cliente.getOutputStream());
    } catch (
    UnknownHostException var9) {
        System.err.println("El servidor no está levantado");
    } catch (Exception var10) {
        System.err.println("Error: " + var10);
    }

        try {

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

