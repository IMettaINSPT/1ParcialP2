package sistemapoliciafederal;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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

    public void Accionar(int menu) {
        int subMenu;
        if (Objects.isNull(sistemaState)) {
            EntradaSalida.mostrarError("No existe informacion en el sistema");
        }

        switch (this.GetMenu()) {
            case 1:
                String domicilio;
                String codigo;
                if (this.sistemaState.getBancos().size() <= 0) {
                    EntradaSalida.mostrarError("Debe existir al menos un banco");
                }
                subMenu = Menu.mostrar("1-Mostrar banco por codigo 2-Mostrar banco por domicilio", "Error.Reintente", 1, 2, 3);

                if (subMenu == -1) {
                    EntradaSalida.mostrarError("Ingreso una opcion invalida");

                } else {
                    if (subMenu == 1) {
                        codigo = EntradaSalida.leerString("Ingrese el codigo del banco a buscar");
                        if (codigo.isEmpty()) {
                            EntradaSalida.mostrarError("Debe ingresar un codigo valido");
                        }
                        this.setConsultaBanco(new ConsultarBancoPorCodigo(this.sistemaState.getBancos(), codigo));

                    } else {
                        domicilio = EntradaSalida.leerString("Ingrese el domicilio del banco a buscar");
                        if (domicilio.isEmpty()) {
                            EntradaSalida.mostrarError("Debe ingresar un domicilio valido");
                        }
                        this.setConsultaBanco(new ConsultarBancoPorDomicilio(this.sistemaState.getBancos(), domicilio));
                    }

                    Banco bco = this.getBanco();
                    if (Objects.isNull(bco)) {
                        EntradaSalida.mostrarError("No existe el banco con los parametros seleccionados");
                    } else {
                        EntradaSalida.mostrarString(bco.getInfoBanco());
                    }
                }
            default:
                EntradaSalida.mostrarError("La opcion ingresada es incorrecta");
        }
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
