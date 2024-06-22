package sistemapoliciafederal;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Control c = new Control();
        c.InitSistema();
        SistemaState sistemaState = new SistemaState();
        
        UsuarioAdmin admin = new UsuarioAdmin(sistemaState);
        VigilanteUsuario vigilante = new VigilanteUsuario(sistemaState);
        InvestigadorUsuario investigador = new InvestigadorUsuario(sistemaState);
        
        try {
            // ACA VA EL LLAMADO A CADA METODO DEL CONTROL PARA HACER ITERATIVO EL SISTEMA
            // c.dummyTest();
            c.dummyDeserializar();
        } catch (IOException | ClassNotFoundException e) {
            EntradaSalida.mostrarString(e.getMessage());
        }
        
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Usuario Administrador");
            System.out.println("2. Usuario Vigilante");
            System.out.println("3. Usuario Investigador");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    admin.mostrarMenu();
                    break;
                case 2:
                    vigilante.mostrarMenu();
                    break;
                case 3:
                    investigador.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta. Intente nuevamente.");
            }
        } while (opcion != 0);
        
        sc.close();
    }
}
