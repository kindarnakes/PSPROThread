package Client.GUI;

import Client.Controller.*;
import Client.Dao.ChamberDao;
import Client.Model.Log;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GUI {

    static public void startGUI() {

        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;


        while (!salir) {

            System.out.println();
            System.out.println("---------------------------MENU-----------------------------");
            System.out.println("1. Simular la lectura de dos sensores de la misma cámara de"
                    + "manera simultánea.");
            System.out.println("2. Simular la lectura de un sensor con valor superior a la temperatura "
                    + "máxima y una apertura de puerta simultánea.");

            System.out.println("3. Ingreso de una nueva cámara frigorífica y lectura de los"
                    + "sensores de temperatura de las cámaras restantes. ");
            System.out.println("4. Resumen del estado de las cámaras.");
            System.out.println("5. Salir");

            try {
                System.out.print("Indica una opción a través del dígito que se muestra:");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        //Llama al método correspondiente.
                        break;
                    case 2:
                        opt2();
                        break;
                    case 3:
                        opt3();
                        break;
                    case 4:
                        opt4();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Indique un dígito válido entre 1-5");
                }

            } catch (InputMismatchException | InterruptedException e) {
                System.out.println("¡Cuidado! Solo puedes insertar números.");
                sc.next();
            }
        }

    }

    private static void opt2() {
        ChamberDao chamberDao = new ChamberDao(1,-30,-25,-26,false,true);
        ThreadUpdateChamber update = new ThreadUpdateChamber(chamberDao);
        ThreadDoor door=new ThreadDoor(chamberDao);
        System.out.println("Cambia de datos");
        update.start();
        door.start();
    }

    private static void opt3() {

        ChamberDao chamberDao = new ChamberDao(4,-15,20,18,false,false);
        ThreadAdd add = new ThreadAdd(chamberDao);
        ChamberDao chamberDao1 = new ChamberDao(chamberDao.findById(1));
        ThreadUpdate up1 = new ThreadUpdate(chamberDao1);
        ThreadUpdate up2 = new ThreadUpdate(chamberDao1);
        add.start();
        up1.start();
        up2.start();
    }

    private static void opt4() throws InterruptedException {
        Log log = Log.getINSTANCE();
        LogViewer logViewer = new LogViewer(log);
        logViewer.start();
        logViewer.join();
    }
}





