/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author IMetta
 */
public class Menu implements Serializable {

    public static int mostrar(String menusMsj, String errorMenuMsj, int cantMinMenu, int cantMaxMenu, int cantReintentos) {
        Scanner scanner = new Scanner(System.in);
        int menuOp = -1;

        if (!menusMsj.isEmpty() && !errorMenuMsj.isEmpty() && cantMinMenu <= cantMaxMenu && cantReintentos >= 0) {
            do {
                EntradaSalida.mostrarString(menusMsj);
                menuOp = scanner.nextInt();
                if (menuOp >= cantMinMenu && menuOp <= cantMaxMenu) {
                    return menuOp;
                }
                cantReintentos--;
                EntradaSalida.mostrarString(errorMenuMsj);
            } while (cantReintentos >= 0);
        }
        return menuOp;

    }
}
