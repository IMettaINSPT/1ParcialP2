/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class UsuarioVigilante extends Usuario {

    @Override
    public int GetMenu() {
        return menu.mostrar("1-MostrarContrato", "Error.Reintente", 1, 1, 3);

    }
    
    public static Usuario crearUsuario(String u, String p) {
       Usuario user = new UsuarioVigilante();
       user.setPass(p);
       user.setUser(u);
       return user;
    }

}
