package sistemapoliciafederal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioAdmin extends Usuario implements IUsuarioConsultante {

    private final SistemaState sistemaState;
    private IConsultaDelitos consultaDelitos;
    private IConsultaSucursal consultaSucursales;
    private IConsultaBanco consultaBanco;
    private IConsultaContrato consultaContrato;
    private IConsultaDelincuente consultaDelincuente;

    private List<Banco> bancos;
    private List<PersonaDetenida> personasDetenidas;
    private List<Juez> jueces;

    public UsuarioAdmin(SistemaState sistemaState) {
        super("admin", "1234"); // Asignar nombre de usuario y contraseña predeterminados
        this.sistemaState = sistemaState;
        this.bancos = sistemaState.getBancos();
        this.personasDetenidas = sistemaState.getDetenidos(); // Inicializar desde sistemaState
        this.jueces = sistemaState.getJueces(); // Inicializar desde sistemaState
    }

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato 7-CargarBanco 8-CargarSucursal 9-CargarJuicio 10-CargarBanda 11-CargarDelincuente 12-CargarVigilante 13-CargarDelito 14-CargarContrato 15-CargarJuez", "Error. Reintente", 1, 15, 3);
    }

    @Override
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            opcion = this.GetMenu();
            this.Accionar(opcion);
        } while (opcion != 0);
    }

    public void Accionar(int menu) {
        int subMenu;
        Scanner sc = new Scanner(System.in);
        
        if (Objects.isNull(sistemaState)) {
            EntradaSalida.mostrarError("No existe informacion en el sistema");
            return;
        }

        switch (menu) {
            case 1:
                // Mostrar información de bancos
                if (this.bancos.isEmpty()) {
                    EntradaSalida.mostrarError("Debe existir al menos un banco");
                    return;
                }
                subMenu = Menu.mostrar("1-Mostrar banco por codigo 2-Mostrar banco por domicilio", "Error. Reintente", 1, 2, 3);
                if (subMenu == 1) {
                    String codigo = EntradaSalida.leerString("Ingrese el codigo del banco a buscar");
                    if (codigo.isEmpty()) {
                        EntradaSalida.mostrarError("Debe ingresar un codigo valido");
                        return;
                    }
                    this.setConsultaBanco(new ConsultarBancoPorCodigo(this.bancos, codigo));
                } else if (subMenu == 2) {
                    String domicilio = EntradaSalida.leerString("Ingrese el domicilio del banco a buscar");
                    if (domicilio.isEmpty()) {
                        EntradaSalida.mostrarError("Debe ingresar un domicilio valido");
                        return;
                    }
                    this.setConsultaBanco(new ConsultarBancoPorDomicilio(this.bancos, domicilio));
                } else {
                    EntradaSalida.mostrarError("Ingreso una opcion invalida");
                    return;
                }
                Banco bco = this.getBanco();
                if (Objects.isNull(bco)) {
                    EntradaSalida.mostrarError("No existe el banco con los parametros seleccionados");
                } else {
                    EntradaSalida.mostrarString(bco.getInfoBanco());
                }
                break;

            case 2:
                // Mostrar información de personas detenidas
                for (PersonaDetenida persona : personasDetenidas) {
                    persona.mostrarInformacion();
                }
                break;

            case 3:
                // Mostrar información de jueces
                for (Juez juez : jueces) {
                    juez.mostrarInformacion();
                }
                break;

            case 4:
                // Agregar banco
                System.out.print("Ingrese el codigo del banco: ");
                String codigoBanco = sc.nextLine();
                System.out.print("Ingrese el domicilio del banco: ");
                String domicilioBanco = sc.nextLine();
                bancos.add(new Banco(codigoBanco, domicilioBanco));
                break;

            case 5:
                // Agregar sucursal
                System.out.print("Ingrese el codigo del banco: ");
                codigoBanco = sc.nextLine();
                Banco banco = buscarBancoPorCodigo(codigoBanco);
                if (banco != null) {
                    System.out.print("Ingrese el codigo de la sucursal: ");
                    String codigoSucursal = sc.nextLine();
                    System.out.print("Ingrese el domicilio de la sucursal: ");
                    String domicilioSucursal = sc.nextLine();
                    System.out.print("Ingrese el numero de empleados de la sucursal: ");
                    int empleados = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    banco.agregarSucursal(new Sucursal(codigoSucursal, domicilioSucursal, empleados));
                } else {
                    System.out.println("Banco no encontrado.");
                }
                break;

            case 6:
                // Agregar vigilante
                System.out.print("Ingrese el codigo del vigilante: ");
                String codigoVigilante = sc.nextLine();
                System.out.print("Ingrese la edad del vigilante: ");
                int edadVigilante = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                Vigilante vigilante = new Vigilante(codigoVigilante, edadVigilante);
                System.out.print("Ingrese el codigo de la sucursal donde trabaja el vigilante: ");
                String codigoSucursalVigilante = sc.nextLine();
                Sucursal sucursalVigilante = buscarSucursalPorCodigo(codigoSucursalVigilante);
                if (sucursalVigilante != null) {
                    sucursalVigilante.agregarVigilante(vigilante);
                } else {
                    System.out.println("Sucursal no encontrada.");
                }
                break;

            case 7:
                // Agregar persona detenida
                System.out.print("Ingrese el codigo de la persona detenida: ");
                String codigoPersona = sc.nextLine();
                System.out.print("Ingrese el nombre completo de la persona detenida: ");
                String nombrePersona = sc.nextLine();
                System.out.print("Ingrese el numero de la banda a la que pertenece (o 0 si no pertenece a ninguna banda): ");
                int numeroBanda = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                Banda banda = numeroBanda != 0 ? new Banda(numeroBanda, 0) : null;
                PersonaDetenida personaDetenida = new PersonaDetenida(codigoPersona, nombrePersona, banda);
                personasDetenidas.add(personaDetenida);
                break;

            case 8:
                // Agregar juez
                System.out.print("Ingrese la clave del juzgado: ");
                String claveJuzgado = sc.nextLine();
                System.out.print("Ingrese el nombre del juez: ");
                String nombreJuez = sc.nextLine();
                System.out.print("Ingrese los años de servicio del juez: ");
                int anosServicio = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                jueces.add(new Juez(claveJuzgado, nombreJuez, anosServicio));
                break;

            case 0:
                // Salir
                System.out.println("Saliendo...");
                break;

            default:
                EntradaSalida.mostrarError("La opcion ingresada es incorrecta");
                break;
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

    private Banco buscarBancoPorCodigo(String codigoBanco) {
        for (Banco banco : bancos) {
            if (banco.getCodigoBanco().equals(codigoBanco)) {
                return banco;
            }
        }
        return null;
    }

    private Sucursal buscarSucursalPorCodigo(String codigoSucursal) {
        for (Banco banco : bancos) {
            for (Sucursal sucursal : banco.getSucursales()) {
                if (sucursal.getCodigoSucursal().equals(codigoSucursal)) {
                    return sucursal;
                }
            }
        }
        return null;
    }
}
