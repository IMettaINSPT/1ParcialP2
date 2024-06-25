package sistemapoliciafederal;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        Control c = new Control();       
        boolean usuarioLogeado;
        c.RestaurarEstadoSistema();
        usuarioLogeado = c.Login();
        if (usuarioLogeado) {
            c.realizarAccionUsuario();
            c.Desloguearse();
        }
    }

}
