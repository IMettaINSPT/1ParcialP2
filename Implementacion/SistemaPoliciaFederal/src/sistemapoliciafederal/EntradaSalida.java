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
        mostrarString(texto + "\n");
        Scanner stringScanner = new Scanner(System.in);
        int msj;
        while (true) {
            try {
                msj = Integer.parseInt(stringScanner.nextLine());
                break; // Exit the loop if input is a valid integer
            } catch (NumberFormatException e) {
                mostrarString("Entrada inválida. Por favor, ingrese un número entero.\n");
            }
        }
        return msj;
    }

    public static boolean leerBoolean(String texto) {
        Scanner stringScanner = new Scanner(System.in);
        String msj;
        boolean entradaValida = false;
        boolean resultado = false;

        do {
            mostrarString(texto);
            mostrarString("Ingrese\nSI o NO");
            msj = stringScanner.nextLine().trim().toUpperCase();

            switch (msj) {
                case "SI":
                    resultado = true;
                    entradaValida = true;
                    break;
                case "NO":
                    resultado = false;
                    entradaValida = true;
                    break;
                default:
                    mostrarString("Entrada no válida. Por favor, ingrese 'SI' o 'NO'.");
                    break;
            }
        } while (!entradaValida);

        return resultado;
    }

    public static void mostrarError(String s) {
        mostrarString("Error: " + s);
    }

    public static void mostrarString(String s) {
        System.out.println(s + "\n");
    }

    public static String leerPassword(String texto) {
        return leerString(texto);
    }
}
