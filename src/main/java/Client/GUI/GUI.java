package Client.GUI;

import Client.Controller.*;
import Client.Dao.ChamberDao;
import Client.Model.Chamber;
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
            System.out.println("1. : Sensor de temperatura");
            System.out.println("2. : : Sensor de puerta abierta");
            System.out.println("3. : Sensor de temperatura");

            try {
                System.out.print("Indica una opción a través del dígito que se muestra:");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        opt1();
                        break;
                    case 2:
                        opt2();
                        break;
                    case 3:
                        opt3();
                        break;

                    default:
                        System.out.println("Indique un dígito válido entre 1-5");
                }

            } catch (InputMismatchException) {
                System.out.println("¡Cuidado! Solo puedes insertar números.");
                sc.next();
            }
        }

    }
    
    
    private static void opt1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la Id del Chamber");
        int id_chamber=sc.nextInt();
        System.out.println("¿Qué sensonr es?");
        System.out.println("------------------");
        System.out.println("1.Sensor 1");
        System.out.println("2.Sensor 2");
        int n_sensor=sc.nextInt();
        Chamber
       /* chamberDao = new ChamberDao(chamberDao.findById(2));
        ThreadUpdate1 s1 = new ThreadUpdate1(chamberDao);
        ThreadUpdate2 s2 = new ThreadUpdate2(chamberDao);*/
      /*  s1.start();
        s2.start();*/
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

}





