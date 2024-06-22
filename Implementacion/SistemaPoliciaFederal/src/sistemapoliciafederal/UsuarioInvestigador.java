package sistemapoliciafederal;

import java.util.List;
import java.util.Scanner;

public class UsuarioInvestigador extends Usuario implements IUsuarioConsultante {

    private final SistemaState sistemaState;
    private IConsultaDelitos consultaDelitos;
    private IConsultaSucursal consultaSucursales;
    private IConsultaBanco consultaBanco;
    private IConsultaContrato consultaContrato;
    private IConsultaDelincuente consultaDelincuente;

    // Nuevos atributos de la segunda clase
    private List<Banco> bancos;
    private List<PersonaDetenida> personasDetenidas;


    // Constructor 
    public UsuarioInvestigador(SistemaState sistemaState, String username, String password, List<Banco> bancos, List<PersonaDetenida> personasDetenidas) {
        super(username, password);
        this.bancos = bancos;
        this.personasDetenidas = personasDetenidas;
        this.sistemaState = sistemaState;
    }

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato", "Error.Reintente", 1, 6, 3);
    }

    public static Usuario crearUsuario(String u, String p, SistemaState sist) {
        Usuario user = new UsuarioInvestigador(sist);
        user.setPass(p);
        user.setUser(u);
        return user;
    }

    @Override
    public void getInfoGeneral() {
        // Imprimir la información de todas las listas en la base de datos.
        System.out.println("Bancos:");
        System.out.println(this.getBanco().getInfoBanco());

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

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Ver información de bancos");
            System.out.println("2. Ver información de personas detenidas");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    for (Banco banco : bancos) {
                        banco.mostrarInformacion();
                    }
                    break;
                case 2:
                    for (PersonaDetenida persona : personasDetenidas) {
                        persona.mostrarInformacion();
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
}
