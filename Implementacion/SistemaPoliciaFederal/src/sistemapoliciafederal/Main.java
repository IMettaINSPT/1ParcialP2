package sistemapoliciafederal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Control c = new Control();
        c.InitSistema();
        try {
            //ACA VA EL LLAMADO A CADA METODO DEL CONTROL PARA HACER ITERATIVO EL SISTEMA
           //c.dummyTest();            
            c.dummyDeserializar();
        } catch (IOException| ClassNotFoundException  e) {
            EntradaSalida.mostrarString(e.getMessage());
        }
    }

}
