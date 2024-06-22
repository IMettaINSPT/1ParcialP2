package sistemapoliciafederal;

/*import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;*/
import java.util.Scanner;

public class EntradaSalida {

    public static char leerChar(String texto) {
        String st = leerString(texto);
        return (st == null || st.length() == 0 ? '0' : st.charAt(0));
    }

    public static String leerString(String texto) {
        mostrarString(texto);
        Scanner stringScanner = new Scanner(System.in);
        String msj = stringScanner.nextLine();
        return msj;
    }

    public static boolean leerBoolean(String texto) {
        mostrarString(texto);
        Scanner stringScanner = new Scanner(System.in);
        String msj = stringScanner.nextLine();        
        return msj.toUpperCase().equals("YES");    
    }

    public static void mostrarError(String s) {       
         System.out.println("Error: " +s);
    }
    public static void mostrarString(String s) {       
         System.out.println(s);
    }

    public static String leerPassword(String texto) {
         return leerString(texto);
    }
}
