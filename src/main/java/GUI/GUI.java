package GUI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GUI {

    static public void startGUI() {

        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("");
            System.out.println("---------------------------MENU-----------------------------");
            System.out.println("1. Simular la lectura de dos sensores de la misma c�mara de"
                    + "manera simult�nea.");
            System.out.println("2. Simular la lectura de un sensor con valor superior a la temperatura "
                    + "m�xima y una apertura de puerta simult�nea.");
                    
            System.out.println("3. Ingreso de una nueva c�mara frigor�fica y lectura de los"
                    + "sensores de temperatura de las c�maras restantes. ");
            System.out.println("4. Resumen del estado de las c�maras.");
            System.out.println("5. Salir");        

            try {
                System.out.print("Indica una opci�n a trav�s del d�gito que se muestra:");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        //Llama al m�todo correspondiente.
                        break;
                    case 2:
                        //Llama al m�todo correspondiente.
                        break;
                    case 3:
                        //Llama al m�todo correspondiente.
                        break;
                    case 4:
                        //Llama al m�todo correspondiente.
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Indique un d�gito v�lido entre 1-5");
                }

            } catch (InputMismatchException e) {
                System.out.println("�Cuidado! Solo puedes insertar n�meros.");
                sc.next();
            }
        }

    }
}
