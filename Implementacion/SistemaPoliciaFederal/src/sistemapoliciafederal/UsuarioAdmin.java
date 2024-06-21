package sistemapoliciafederal;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UsuarioAdmin extends Usuario implements IUsuarioConsultante {

    private SistemaState sistemaState;
    private IConsultaDelitos consultaDelitos;
    private IConsultaSucursal consultaSucursales;
    private IConsultaBanco consultaBanco;
    private IConsultaContrato consultaContrato;
    private IConsultaDelincuente consultaDelincuente;

    public UsuarioAdmin(SistemaState sistemaState) {
    }

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato 7-Crear Usuario 8-Cargar Banco 9-Cargar Sucursal 10-Cargar Juicio 11-Carga Banda 12-Cargar Delincuente 13-Cargar Vigilante 14-Cargar Delito 15-Cargar Contrato 16-Cargar Juez 17-Guardar Sistema 18-Agregar delincuente a banda", "Error.Reintente", 1, 18, 3);
    }

    @Override
    public void accionar() {
        int subMenu;
        if (Objects.isNull(sistemaState)) {
            EntradaSalida.mostrarError("No existe informacion en el sistema");
        }
        try {
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
                case 7:
                    Usuario user = this.crearUsuario();
                    this.sistemaState.addUsuario(user);
                    if (Objects.isNull(user)) {
                        EntradaSalida.mostrarError("Ocurrio un error crando el usuario");
                    }
                    break;
                case 8:
                    String codigoBanco = EntradaSalida.leerString("Ingrese el codigo del banco");
                    if (codigoBanco.isEmpty()) {
                        EntradaSalida.mostrarError("El codigo de banco no puede estar vacio");
                        break;
                    }
                    String domicilioBanco = EntradaSalida.leerString("Ingrese el domicilio del banco");
                    if (domicilioBanco.isEmpty()) {
                        EntradaSalida.mostrarError("El domicilio no puede estar vacio");
                        break;
                    }
                    Banco bco = new Banco(codigoBanco, domicilioBanco);
                    if (Objects.isNull(bco)) {
                        EntradaSalida.mostrarError("Ocurrio un error cargando el banco ");
                        break;
                    }
                    this.sistemaState.addBanco(bco);
                    break;
                case 9:
                    Banco unBanco = this.obtenerBanco();
                    if (Objects.isNull(unBanco)) {
                        String codigoSucursal = EntradaSalida.leerString("Ingrese el codigo de la sucursal a asociar al banco");
                        if (codigoSucursal.isEmpty()) {
                            EntradaSalida.mostrarError("El codigo de la sucursal no puede estar vacio");
                            break;
                        }
                        String domicilioSucursal = EntradaSalida.leerString("Ingrese el domicilio de la sucursal a asociar al banco");
                        if (domicilioSucursal.isEmpty()) {
                            EntradaSalida.mostrarError("El domicilio de la sucursal no puede estar vacio");
                            break;
                        }
                        int cantEmpleados = EntradaSalida.leerEntero("Ingrese el domicilio de la sucursal a asociar al banco");
                        if (cantEmpleados <= 0) {
                            EntradaSalida.mostrarError("El nro de empleados no puede ser 0");
                            break;
                        }
                        unBanco.addSucursal(codigoSucursal, domicilioSucursal, cantEmpleados);
                        if (Objects.isNull(unBanco)) {
                            EntradaSalida.mostrarError("Ocurrio un error cargando la sucursal del baco");
                            break;
                        }
                        this.sistemaState.actualizarBanco(unBanco);
                    }
                    break;
                case 10:
                    Date fechaJuicio = EntradaSalida.leerDate("Ingrese fecha del juicio");
                    IDelito delito = this.obtenerDelito();
                    Date fechaInicioCondena = EntradaSalida.leerDate("Ingrese fecha inicio condena");
                    Date fechaFinCondena = EntradaSalida.leerDate("Ingrese fecha fin condea");
                    IJuez juez = this.obtenerJuez();
                    if (Objects.isNull(delito)) {
                        EntradaSalida.mostrarError("El delito ingresado no existe");
                        break;
                    }
                    if (Objects.isNull(fechaJuicio)) {
                        EntradaSalida.mostrarError("La fecha del juicio es incorrecta");
                        break;
                    }
                    Juicio juicio = new Juicio(juez, fechaJuicio, delito, fechaInicioCondena, fechaFinCondena);
                    if (Objects.isNull(juicio)) {
                        EntradaSalida.mostrarError("Ocurrio un error cargando el juez");
                        break;
                    }
                    this.sistemaState.addJuicio(juicio);
                    break;
                case 11:
                    PersonaDetenida personaDetenida;
                    boolean sigueCargando;

                    int codigoBanda = EntradaSalida.leerEntero("Ingrese el numero de la banda a cargar");
                    if (codigoBanda <= 0) {
                        EntradaSalida.mostrarError("El codigo de la banda no puede ser 0");
                        break;
                    }

                    Banda banda = new Banda(codigoBanda);
                    if (Objects.isNull(banda)) {
                        EntradaSalida.mostrarError("Ocurrio un error cargando la banda");
                        break;
                    }

                    do {
                        sigueCargando = EntradaSalida.leerBoolean("¿Desea cargar un miembro de la banda?");
                        if (sigueCargando) {
                            personaDetenida = this.obtenerPersonaDetenida();
                            if (Objects.isNull(personaDetenida)) {
                                EntradaSalida.mostrarError("El delincuente no existe");
                                break;
                            } else {
                                banda.addMiembro(personaDetenida);
                            }
                        }

                    } while (sigueCargando);

                    this.sistemaState.actualizarBanda(banda);
                    break;
                case 12:
                    String codigoDelincuente = EntradaSalida.leerString("Ingrese el codigo del delincuente a buscar");
                    if (codigoDelincuente.isEmpty()) {
                        EntradaSalida.mostrarError("El codigo del delincuete no puede estar vacio");
                        break;
                    }
                    String nombreDelicuente = EntradaSalida.leerString("Ingrese el nombre del delincuente a buscar");
                    if (nombreDelicuente.isEmpty()) {
                        EntradaSalida.mostrarError("El nombre del delincuente no puede estar vacio");
                        break;
                    }
                    PersonaDetenida delincuente = new PersonaDetenida(codigoDelincuente, nombreDelicuente);
                    if (Objects.isNull(delincuente)) {
                        EntradaSalida.mostrarError("Ocurrio un error cargando el delincuente");
                    }
                    boolean perteneceABanda = EntradaSalida.leerBoolean("¿El delincuente pertenece a una banda?");
                    if (perteneceABanda) {
                        Banda bandita = this.obtenerBanda();
                        bandita.addMiembro(delincuente);
                        this.sistemaState.actualizarBanda(bandita);
                    }
                    this.sistemaState.addDelincuente(delincuente);
                    break;
                case 13:
                    String codVig = EntradaSalida.leerString("Ingrese codigo del vigilante");
                    if (codVig.isEmpty()) {
                        EntradaSalida.mostrarError("El codigo no puede estar vacio");
                    }
                    boolean esConArma = EntradaSalida.leerBoolean("¿Contrato con arma?");
                    Contrato contrato = this.obtenerContrato();
                    int edad = EntradaSalida.leerEntero("Ingrese la edad");
                    if (edad <= 0) {
                        EntradaSalida.mostrarError("La edad no puede ser 0");
                        break;
                    }
                    if (Objects.isNull(contrato)) {
                        break;
                    }
                    Vigilante vig = new Vigilante(codVig, edad, esConArma, contrato);
                    if (Objects.isNull(vig)) {
                        EntradaSalida.mostrarError("Ocurrio un error cargado el vigilante");
                    }
                    this.sistemaState.addVigilante(vig);
                    break;
                case 14:
                    //Sucursal donde se carga el delito        
                    Sucursal suc = this.obtenerSucursal();
                    if (Objects.isNull(suc)) {
                        Date fechaDelito = EntradaSalida.leerDate("Ingrese la fecha del delito a buscar");
                        if (Objects.isNull(fechaDelito)) {
                            EntradaSalida.mostrarError("La fecha del delito no es correcta");
                            break;
                        }
                        PersonaDetenida personaD = obtenerPersonaDetenida();
                        if (Objects.isNull(personaD)) {
                            EntradaSalida.mostrarError("La persona detenida no existe");
                            break;
                        }
                        boolean huboCondena = EntradaSalida.leerBoolean("¿Hubo condena?");
                        Delito d = new Delito(fechaDelito, personaD, huboCondena);
                        if (Objects.isNull(d)) {
                            EntradaSalida.mostrarError("Hubo un error cargado el delito");
                            break;
                        }
                        this.sistemaState.addDelito(d);
                        suc.cargarDelito(d);
                        this.sistemaState.actualizarBanco(suc);
                    }
                case 15:
                    Date fechaContrato = EntradaSalida.leerDate("Ingrese la fecha del contrato");
                    if (Objects.isNull(fechaContrato)) {
                        EntradaSalida.mostrarError("Fecha incorrecta");
                        break;
                    }
                    Sucursal sucBcoCont = this.obtenerSucursal();
                    if (Objects.isNull(sucBcoCont)) {
                        EntradaSalida.mostrarError("La sucursal del banco no existe");
                        break;
                    }
                    Vigilante vigCon = this.obtenerVigilanteLibre();
                    if (Objects.isNull(vigCon)) {
                        break;
                    }
                    boolean esConArmaCont = EntradaSalida.leerBoolean("¿Contrato con arma?");
                    sucBcoCont.GenerarContrato(esConArmaCont, fechaContrato, vigCon);
                    this.sistemaState.actualizarBanco(sucBcoCont);
                case 16:
                    String claveInternaJuzgado = EntradaSalida.leerString("Ingrese la clave interna del juzgado");
                    if (claveInternaJuzgado.isEmpty()) {
                        EntradaSalida.mostrarError("La clave interna del juzgado no puede ser vacia");
                        break;
                    }
                    String nombreJuez = EntradaSalida.leerString("Ingrese el nombre del juez");
                    if (nombreJuez.isEmpty()) {
                        EntradaSalida.mostrarError("El nombre del juez no puede ser vacio");
                        break;
                    }
                    int añosServicio = EntradaSalida.leerEntero("Ingrese años de servicio");
                    if (añosServicio <= 0) {
                        EntradaSalida.mostrarError("Los años de servicio no pueden ser 0");
                        break;
                    }
                    Juez unJuez = new Juez(claveInternaJuzgado, nombreJuez, añosServicio);
                    if (Objects.isNull(unJuez)) {
                        EntradaSalida.mostrarError("Ocurrio un error al cargar el juez");
                    }
                    this.sistemaState.addJuez(unJuez);

                case 17: {
                    this.sistemaState.serializar("PoliciaFederal.bin");
                }
                case 18:
                    banda = this.obtenerBanda();
                    if (Objects.nonNull(banda)) {
                        do {
                            sigueCargando = EntradaSalida.leerBoolean("¿Desea cargar un miembro de la banda?");
                            if (sigueCargando) {
                                personaDetenida = this.obtenerPersonaDetenida();
                                if (Objects.isNull(personaDetenida)) {
                                    EntradaSalida.mostrarError("El delincuente no existe");
                                    break;
                                } else {
                                    banda.addMiembro(personaDetenida);
                                }
                            }
                        } while (sigueCargando);
                    }

                default:
                    EntradaSalida.mostrarError("La opcion ingresada es incorrecta");
            }

        } catch (IOException ex) {
            EntradaSalida.mostrarError(ex.getMessage());
        }
    }

    public void serializar(String path) {
        try {
            sistemaState.serializar(path);
        } catch (IOException ex) {
            EntradaSalida.mostrarError(ex.getMessage());
        }
    }

    public Usuario crearUsuario() {
        Usuario user = null;
        int tipoUsuario = Menu.mostrar("1- ADMIN 2-INVESTIGADOR 3-VIGILANTE 4-SALIR", "Opcion incorrecta", 1, 4, 3);

        if (tipoUsuario == -1) {
            EntradaSalida.mostrarError("Ingreso una opcion invalida");

        } else {
            String usuario = "";
            String pass = "";
            switch (tipoUsuario) {
                case 1:
                    user = UsuarioAdmin.nuevoUsuario(usuario, pass, sistemaState);
                    break;
                case 2:
                    user = UsuarioInvestigador.nuevoUsuario(usuario, pass, sistemaState);
                    break;
                case 3:
                    Vigilante vigilante = this.obtenerVigilante();
                    if (Objects.nonNull(vigilante)) {
                        user = UsuarioVigilante.nuevoUsuario(usuario, pass);
                        ((UsuarioVigilante) user).setVigilanteAsociado(vigilante);
                    }
                    break;
            }
        }
        if (Objects.isNull(user)) {
            EntradaSalida.mostrarError("Error creando el usuario");
        }
        return user;
    }

    private Vigilante obtenerVigilanteLibre() {
        Vigilante v = null;
        String codigo = EntradaSalida.leerString("Ingrese el codigo del vigilante a buscar");

        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo del vigilante no puede ser vacio");
            return v;
        }
        for (Vigilante vig : this.sistemaState.getVigilantesSinContrato()) {
            if (vig.soyElVigilante(codigo)) {
                return vig;
            }
        }
        EntradaSalida.mostrarError("No se encuentra el vigilante para el codigo ingresado");
        return v;
    }

    private Vigilante obtenerVigilante() {
        Vigilante v = null;
        String codigo = EntradaSalida.leerString("Ingrese el codigo del vigilante a buscar");

        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo del vigilante no puede ser vacio");
            return v;
        }
        for (Vigilante vig : this.sistemaState.getVigilantes()) {
            if (vig.soyElVigilante(codigo)) {
                return vig;
            }
        }
        EntradaSalida.mostrarError("No se encuentra el vigilante para el codigo ingresado");
        return v;
    }

    private IDelito obtenerDelito() {
        Delito d = null;
        Date fechaDelito = EntradaSalida.leerDate("Ingrese la fecha del delito a buscar");
        if (Objects.isNull(fechaDelito)) {
            EntradaSalida.mostrarError("La fecha del delito no es correcta");
            return d;
        }
        PersonaDetenida personaD = obtenerPersonaDetenida();
        if (Objects.isNull(personaD)) {
            EntradaSalida.mostrarError("La persona detenida no existe");
            return d;
        }
        boolean condena = EntradaSalida.leerBoolean("¿El delito es con condena?");

        for (IDelito dd : this.sistemaState.getDelitos()) {
            if (((Delito) dd).soyElDelito(fechaDelito, personaD, condena)) {
                return ((Delito) dd);
            }
        }

        return d;
    }

    private Contrato obtenerContrato() {
        Contrato contrato = null;
        Date fechaContrato = EntradaSalida.leerDate("Ingrese la fecha del contrato");
        if (Objects.isNull(fechaContrato)) {
            EntradaSalida.mostrarError("Fecha incorrecta");
            return null;
        }
        Sucursal suc = this.obtenerSucursal();
        if (Objects.isNull(suc)) {
            EntradaSalida.mostrarError("La sucursal del banco no existe");
            return null;
        }
        boolean esConArma = EntradaSalida.leerBoolean("¿Contrato con arma?");

        for (Contrato c : suc.getContratos()) {
            if (c.soyElContrato(fechaContrato, esConArma)) {
                return c;
            }
        }
        EntradaSalida.mostrarError("No se encuentra un contrato para la fecha ingresada");
        return contrato;
    }

    private IJuez obtenerJuez() {
        IJuez juez = null;
        String codigoInternaJuzgado = EntradaSalida.leerString("Ingrese la clave interna del juzgado");
        for (IJuez j : this.sistemaState.getJueces()) {
            if (((Juez) j).soyElJuez(codigoInternaJuzgado)) {
                return j;
            }
        }
        EntradaSalida.mostrarError("El codigo ingresado no pertenece a un juez");
        return juez;
    }

    private PersonaDetenida obtenerPersonaDetenida() {
        PersonaDetenida p = null;
        String codigoPersona = EntradaSalida.leerString("Ingrese el codigo del delincuente");
        for (PersonaDetenida pd : this.sistemaState.getDelincuentes()) {
            if (pd.soyElDelincuente(codigoPersona)) {
                return pd;
            }
        }
        EntradaSalida.mostrarError("El codigo ingresado no pertenece a un delincuente");
        return p;
    }

    private Sucursal obtenerSucursal() {
        Sucursal sucursal = null;
        Banco banco = this.obtenerBanco();
        if (Objects.isNull(banco)) {
            EntradaSalida.mostrarError("El banco no existe");
            return null;
        }
        String codigo = EntradaSalida.leerString("Ingrese el codigo de la sucursal en la cual sera contratado");
        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo  no puede ser vacio");
            return sucursal;
        }
        for (Sucursal suc : banco.getSucursales()) {
            if (suc.soyLaSucursal(codigo)) {
                return suc;
            }
        }
        EntradaSalida.mostrarError("No se encuentra la sucursal para el codigo ingresado");
        return sucursal;
    }

    private Banco obtenerBanco() {
        Banco bco = null;
        String codigo = EntradaSalida.leerString("Ingrese el codigo del banco a buscar");
        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo del banco no puede ser vacio");
            return bco;
        }
        for (Banco banco : this.sistemaState.getBancos()) {
            if (banco.soyElBanco(codigo)) {
                return banco;
            }
        }
        EntradaSalida.mostrarError("No se encuentra el banco para el codigo ingresado");
        return bco;
    }

    private Banda obtenerBanda() {
        int nroBanda = EntradaSalida.leerEntero("Ingrese el nro de la banda");
        if (nroBanda <= 0) {
            EntradaSalida.mostrarError("El nro de banda no puede ser 0");
            return null;
        }
        for (Banda b : this.sistemaState.getBandas()) {
            if (b.soyLaBanda(nroBanda)) {
                return b;
            }
        }
        EntradaSalida.mostrarError("No se encontro ninguna banda");
        return null;

    }

    public static Usuario nuevoUsuario(String u, String p, SistemaState sist) {
        UsuarioAdmin user = new UsuarioAdmin(sist);
        user.setPass(p);
        user.setSistemaState(sist);
        user.setUser(u);
        return user;
    }

    @Override
    public void setSistemaState(SistemaState sistemaState) {
        this.sistemaState = sistemaState;
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
