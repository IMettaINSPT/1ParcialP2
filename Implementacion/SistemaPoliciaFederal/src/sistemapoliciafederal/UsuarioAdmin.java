package sistemapoliciafederal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioAdmin extends Usuario implements IUsuarioConsultante {
    SistemaState sistemaState ;

    public UsuarioAdmin(SistemaState sistemaState)
    {
        this.sistemaState = sistemaState;
    }
    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato 7-CargarBanco 8-CargarSucursal 9-CargarJuici 10-CargaBanda 11-CargarDelincuente 12-CargarVigilante 13-CargarDelito 14-CargarContrato 15-CargarJuez", "Error.Reintente", 1, 15, 3);
    }

    public void serializar()
    {
       try {
           sistemaState.serializar("");
       } catch (IOException ex) {
           Logger.getLogger(UsuarioAdmin.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void deSerializar()
    {
       try {
           sistemaState.deSerializar("");
       } catch (IOException | ClassNotFoundException ex) {
           Logger.getLogger(UsuarioAdmin.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }
    
    public static Usuario crearUsuario(String u, String p, SistemaState sist) {
       Usuario user = new UsuarioAdmin(sist);
       user.setPass(p);
       user.setUser(u);
       return user;
    }
    

}
