package sistemapoliciafederal;

import java.text.*;
import java.util.Date;
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

    public static Date leerDate(String texto) {
        try {
            mostrarString(texto);
            Scanner stringScanner = new Scanner(System.in);
            String fechaComoTexto = stringScanner.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse(fechaComoTexto);
            return fecha;
        } catch (ParseException ex) {
            EntradaSalida.mostrarString("Error obteniendo la fecha");
            return null;
        }
    }

    public static int leerEntero(String texto) {
        mostrarString(texto);
        Scanner stringScanner = new Scanner(System.in);
        int msj = stringScanner.nextInt();
        return msj;
    }

    public static boolean leerBoolean(String texto) {
        mostrarString(texto);
        Scanner stringScanner = new Scanner(System.in);
        String msj = stringScanner.nextLine();
        return msj.toUpperCase().equals("YES");
    }

    public static void mostrarError(String s) {
        System.out.println("Error: " + s);
    }

    public static void mostrarString(String s) {
        System.out.println(s);
    }

    public static String leerPassword(String texto) {
        return leerString(texto);
    }
}
