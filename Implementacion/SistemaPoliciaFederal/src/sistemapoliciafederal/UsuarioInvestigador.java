/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class UsuarioInvestigador extends Usuario implements IUsuarioConsultante {

    SistemaState sistemaState ;

    public UsuarioInvestigador(SistemaState sistemaState)
    {
        this.sistemaState = sistemaState;
    }

     @Override
    public int GetMenu() {
        return menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato", "Error.Reintente", 1, 6, 3);
   }   
      public static Usuario crearUsuario(String u, String p, SistemaState sist) {
       Usuario user = new UsuarioInvestigador(sist);
       user.setPass(p);
       user.setUser(u);
       return user;
    }
}
