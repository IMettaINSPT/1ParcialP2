/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class UsuarioAdmin extends Usuario implements IUsuarioConsultante {
   private static DB DB ;
    
    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato 7-CargarBanco 8-CargarSucursal 9-CargarJuici 10-CargaBanda 11-CargarDelincuente 12-CargarVigilante 13-CargarDelito 14-CargarContrato 15-CargarJuez", "Error.Reintente", 1, 15, 3);
    }

    public void serializar()
    {
      DB.serializar();
    }
    
    public void deSerializar()
    {
      DB.serializar();
    }
    
    public static Usuario crearUsuario(String u, String p) {
       DB= new DB();
       Usuario user = new UsuarioAdmin();
       user.setPass(p);
       user.setUser(u);
       return user;
    }
    

}
