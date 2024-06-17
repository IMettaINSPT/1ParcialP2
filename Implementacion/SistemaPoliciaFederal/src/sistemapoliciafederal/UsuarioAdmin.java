package sistemapoliciafederal;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioAdmin extends Usuario implements IUsuarioConsultante {

    private final SistemaState sistemaState;
    private IConsultaDelitos consultaDelitos;
    private IConsultaSucursal consultaSucursales;
    private IConsultaBanco consultaBanco;
    private IConsultaContrato consultaContrato;
    private IConsultaDelincuente consultaDelincuente;

    public UsuarioAdmin(SistemaState sistemaState) {
        this.sistemaState = sistemaState;
    }

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato 7-CargarBanco 8-CargarSucursal 9-CargarJuici 10-CargaBanda 11-CargarDelincuente 12-CargarVigilante 13-CargarDelito 14-CargarContrato 15-CargarJuez", "Error.Reintente", 1, 15, 3);
    }

    public void serializar() {
        try {
            sistemaState.serializar("");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deSerializar() {
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

    // OMG FIX: todos los gets de los objetos deberian utilizar 
    @Override
    public void getInfoGeneral() {
        // Imprimir la información de todas las listas en la base de datos.
        System.out.println("Bancos:");
        for (Banco banco : sistemaState.getBancos()) {
            System.out.println(banco);
        }

        System.out.println("\nDelitos:");
        for (IDelito delito : this.getDelitos()) {
            System.out.println(delito.getInfoCompletaDelito());
        }

        System.out.println("\nJuicios:");
        for (Juicio juicio : sistemaState.getJuicios()) {
            System.out.println(juicio.getInfoJuicio());
        }

        System.out.println("\nVigilantes:");
        for (Vigilante vigilante : sistemaState.getVigilantes()) {
            System.out.println(vigilante.getInfoVigilante());
        }

        System.out.println("\nJueces:");
        for (Juez juez : sistemaState.getJueces()) {
            System.out.println(juez.getInfoJuez());
        }

        System.out.println("\nBandas:");
        for (Banda banda : sistemaState.getBandas()) {
            System.out.println(banda);
        }
    }

    @Override
    public void setConsultaDelitos(IConsultaDelitos con) {
        this.consultaDelitos = con;
    }

    @Override
    public List<IDelito> getDelitos() {
        return this.consultaDelitos.getDelitos();
    }

    @Override
    public void setConsultaSucursales(IConsultaSucursal con) {
        this.consultaSucursales = con;
    }

    @Override
    public List<Sucursal> getSucursales() {
        return this.consultaSucursales.getSucursales();
    }

    @Override
    public void setConsultaBanco(IConsultaBanco con) {
        this.consultaBanco = con;
    }

    @Override
    public Banco getBanco() {
        return this.consultaBanco.getBanco();
    }

    @Override
    public void setConsultaContrato(IConsultaContrato con) {
        this.consultaContrato = con;
    }

    @Override
    public List<Contrato> getContratos() {
        return this.consultaContrato.getContratos();
    }

    @Override
    public void setConsultaDelincuente(IConsultaDelincuente con) {
        this.consultaDelincuente = con;
    }

    @Override
    public List<PersonaDetenida> getDelincuentes() {
        return this.consultaDelincuente.getDelincuentes();
    }

}
