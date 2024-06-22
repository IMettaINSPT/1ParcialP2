
package sistemapoliciafederal;

import java.util.List;
import java.util.Scanner;

public class UsuarioVigilante extends Usuario {
    private Vigilante vigilante;
    private IConsultaContrato consultaContrato;
    private SistemaState sistemaState;

    // Constructor
    public UsuarioVigilante(SistemaState sistemaState, String username, String password, Vigilante vigilante) {
        this.sistemaState = sistemaState;	
        super(username, password);
        this.vigilante = vigilante;
    }
    

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarContrato", "Error.Reintente", 1, 1, 3);
    }

    public static Usuario crearUsuario(String u, String p) {
        Usuario user = new UsuarioVigilante();
        user.setPass(p);
        user.setUser(u);
        return user;
    }

    /**
     * @return the vigilante
     */
    public Vigilante getVigilante() {
        return vigilante;
    }

    /**
     * @param vigilante the vigilante to set
     */
    public void setVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

    @Override
    public void getInfoGeneral() {
        System.out.println("\nVigilante:");
        System.out.println(vigilante.getInfoVigilante());
    }

    public void setConsultaContrato(IConsultaContrato con) {
        this.consultaContrato = con;
    }

    public List<Contrato> getContratos() {
        return this.consultaContrato.getContratos();
    }

    @Override
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Ver mis datos");
            System.out.println("2. Ver información de vigilantes");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            
            this.Accionar(opcion);
        } while (opcion != 0);
        
        sc.close();
    }

    public void Accionar(int menu) {
        switch (menu) {
            case 1:
                vigilante.mostrarInformacion();
                break;
            case 2:
                if (sistemaState != null) {
                    List<Vigilante> vigilantes = sistemaState.getVigilantes();
                    for (Vigilante vigilante : vigilantes) {
                        vigilante.mostrarInformacion();
                    }
                } else {
                    System.out.println("SistemaState no está inicializado.");
                }
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                EntradaSalida.mostrarError("La opción ingresada es incorrecta");
                break;
        }
    }
}
