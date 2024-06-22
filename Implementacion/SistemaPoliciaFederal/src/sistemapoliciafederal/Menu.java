package sistemapoliciafederal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author IMetta
 */
public class Menu implements Serializable {

    private List<Usuario> usuarios;
    private SistemaState sistemaState;

    public Menu() {
        this.usuarios = new ArrayList<>();
        this.sistemaState = new SistemaState();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        // Crear usuarios de ejemplo
        usuarios.add(new AdministradorUsuario("admin", "1234", sistemaState));
        // Puedes agregar otros usuarios (vigilantes, investigadores) según sea necesario
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String username = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = sc.nextLine();

        Usuario usuario = autenticar(username, password);

        if (usuario != null) {
            usuario.mostrarMenu();
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private Usuario autenticar(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

public static int mostrar(String menusMsj, String errorMenuMsj, int cantMinMenu, int cantMaxMenu, int cantReintentos) { 
Scanner scanner = new Scanner(System.in); 
int menuOp = -1; 
if (!menusMsj.isEmpty() && !errorMenuMsj.isEmpty() && cantMinMenu <= cantMaxMenu && cantReintentos >= 0) { 
do { EntradaSalida.mostrarString(menusMsj); menuOp = scanner.nextInt(); 
if (menuOp >= cantMinMenu && menuOp <= cantMaxMenu) {
 return menuOp; 
} 
cantReintentos--; 
EntradaSalida.mostrarString(errorMenuMsj);
 } while (cantReintentos >= 0);
 } return menuOp; 
}                
    

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }
} 
