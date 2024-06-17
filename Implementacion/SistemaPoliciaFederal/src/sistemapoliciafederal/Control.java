package sistemapoliciafederal;

import java.io.IOException;
import java.util.List;

public class Control {
// ver si puede quedar final
    private  final SistemaState sistemaState = new SistemaState();
  
    public void RestaurarEstadoSistema()
    {
        try {
            sistemaState.deSerializar("");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public List<Usuario> GetUsuariosSistema()
    {
        return this.sistemaState.getUsuarios();
    }
    
    public void RelizarConsultasInvestigador(UsuarioInvestigador user)
    {

       IConsultaBanco consultaBanco = new ConsultarBancoPorCodigo(this.sistemaState.getBancos(),"1");
       user.setConsultaBanco(consultaBanco);
       //SEGUIRR Y PROBAR 

    }
    
    public void crearUsuario() {
        Usuario user = null;
        int tipoUsuario = Menu.mostrar("1- ADMIN 2-INVESTIGADOR 3-VIGILANTE 4-SALIR", "Opcion incorrecta", 1, 4, 3);

        if (tipoUsuario != -1) {
            String usuario = "";
            String pass = "";
            switch (tipoUsuario) {
                case 1:
                    user = UsuarioAdmin.crearUsuario(usuario, pass, sistemaState);
                    break;
                case 2:
                    user = UsuarioInvestigador.crearUsuario(usuario, pass, sistemaState);
                    break;
                case 3:
                    user = UsuarioVigilante.crearUsuario(usuario, pass);
                    break;
                case 4:
                    System.out.println("Adios");
                    break;
            }        
            
        }
    }
}
