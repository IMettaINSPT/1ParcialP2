/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.Serializable;

public class Menu implements Serializable {

    public static int mostrar(String menusMsj, String errorMenuMsj, int cantMinMenu, int cantMaxMenu, int cantReintentos) {
        int menuOp = -1;

        if (!menusMsj.isEmpty() && !errorMenuMsj.isEmpty() && cantMinMenu <= cantMaxMenu && cantReintentos >= 0) {
            do {
                menuOp = EntradaSalida.leerEntero(menusMsj);
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
