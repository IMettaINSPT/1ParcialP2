
package sistemapoliciafederal;

import java.util.List;


public class UsuarioVigilante extends Usuario {
   private Vigilante vigilante ;
    private IConsultaContrato consultaContrato;
    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarContrato", "Error.Reintente", 1, 1, 3);
    }
    
    public static Usuario crearUsuario(String u, String p) {
       Usuario user = new UsuarioVigilante();
       user.setPass(p);
       user.setUser(u);
       return user;
    }

    /**
     * @return the vigilante
     */
    public Vigilante getVigilante() {
        return vigilante;
    }

    /**
     * @param vigilante the vigilante to set
     */
    public void setVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }
    
        @Override
    public void getInfoGeneral() {
        System.out.println("\nVigilante:");
            System.out.println(vigilante.getInfoVigilante());        
    }

    public void setConsultaContrato(IConsultaContrato con) {
        this.consultaContrato = con;
    }

    public List<Contrato> getContratos() {
        return this.consultaContrato.getContratos();
    }


}
