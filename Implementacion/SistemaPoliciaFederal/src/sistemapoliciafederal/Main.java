package sistemapoliciafederal;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Control c = new Control();
        try {
            //ACA VA EL LLAMADO A CADA METODO DEL CONTROL PARA HACER ITERATIVO EL SISTEMA
            c.crearUsuario();
        } catch (NullPointerException e) {
            EntradaSalida.mostrarString(e.getMessage());
        }
    }
// HARCODE Metodo para obtener un usuario de algun tipo especifico para usar todas las funcionalidades
    private static Usuario getUsuarioPorTIpo(Control c, String tipoUsuario) {

        List<UsuarioAdmin> admins = new ArrayList<>();
        List<UsuarioInvestigador> investigadores = new ArrayList<>();
        List<UsuarioVigilante> vigilantes = new ArrayList<>();
        int cantidad;
        Random rand = new Random();

        for (Usuario u : c.GetUsuariosSistema()) {

            if (u instanceof UsuarioAdmin) {
                admins.add((UsuarioAdmin) u);
            }

            if (u instanceof UsuarioInvestigador) {
                investigadores.add((UsuarioInvestigador) u);
            }

            if (u instanceof UsuarioVigilante) {
                vigilantes.add((UsuarioVigilante) u);
            }
        }
        switch (tipoUsuario) {
            case "ADMIN":
                cantidad = admins.size();
                return admins.get(rand.nextInt(cantidad));
            case "INV":
                cantidad = investigadores.size();
                return investigadores.get(rand.nextInt(cantidad));
            default:
                cantidad = vigilantes.size();
                return vigilantes.get(rand.nextInt(cantidad));
        }
    }
}
