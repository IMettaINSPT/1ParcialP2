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
                        break;

                    }
                    subMenu = Menu.mostrar("1-Mostrar banco por codigo 2-Mostrar banco por domicilio", "Error.Reintente", 1, 2, 3);

                    if (subMenu == -1) {
                        EntradaSalida.mostrarError("Ingreso una opcion invalida");
                        break;

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
                case 2:

                    String codigoSucursal;

                    Banco bco = this.sistemaState.obtenerBanco();

                    if (Objects.isNull(bco)) {
                        EntradaSalida.mostrarError("Debe ingresar un banco valido");
                        break;

                    }
                    subMenu = Menu.mostrar("1-Mostrar Sucursales por Banco, 2-Mostrar Sucursal por Codigo", "Error.Reintente", 1, 2, 3);

                    if (subMenu == -1) {
                        EntradaSalida.mostrarError("Ingreso una opcion invalida");

                    } else {
                        if (subMenu == 1) {
                            this.setConsultaSucursales(new ConsultarSucursalesPorBanco(bco));
                        } else {
                            codigoSucursal = EntradaSalida.leerString("Ingrese el codigo de la sucursal a buscar");
                            if (codigoSucursal.isEmpty()) {
                                EntradaSalida.mostrarError("Debe ingresar un codigo de sucursal valido");
                                break;
                            }
                            this.setConsultaSucursales(new ConsultarSucursalPorCodigo(bco.getSucursales(), codigoSucursal));
                        }

                        List<Sucursal> sucursales = this.getSucursales();
                        if (sucursales.isEmpty()) {
                            EntradaSalida.mostrarError("No existen sucursales con los parametros seleccionados");
                            break;
                        } else {
                            for (Sucursal sucursal : sucursales) {
                                EntradaSalida.mostrarString(sucursal.getInfoSucursal());
                            }
                        }
                        break;
                    }
                case 3:

                    if (!this.sistemaState.getDelincuentes().isEmpty()) {
                        EntradaSalida.mostrarError("Debe existir al menos un delincuente");
                    }
                    subMenu = Menu.mostrar("1-Mostrar Delincuente por Nombre, 2-Mostrar Delincuente por Codigo, 3-Mostrar Delincuente por Sucursal", "Error.Reintente", 1, 3, 3);

                    if (subMenu == -1) {
                        EntradaSalida.mostrarError("Ingreso una opcion invalida");

                    } else {
                        switch (subMenu) {

                            case 1:
                                String nombre = EntradaSalida.leerString("Ingrese el nombre del delincuente a buscar");
                                if (nombre.isEmpty()) {
                                    EntradaSalida.mostrarError("Debe ingresar un nombre valido");
                                    break;
                                }
                                this.setConsultaDelincuente(new ConsultarDelincuentePorNombre(this.sistemaState.getDelincuentes(), nombre));

                            case 2:
                                String codigoDelincuente = EntradaSalida.leerString("Ingrese el codigo del delincuente a buscar");
                                if (codigoDelincuente.isEmpty()) {
                                    EntradaSalida.mostrarError("Debe ingresar un Codigo de Delincuente valido");
                                    break;
                                }
                                this.setConsultaDelincuente(new ConsultarDelincuentesPorCodigo(this.sistemaState.getDelincuentes(), codigoDelincuente));

                            case 3:
                                Sucursal suc = this.sistemaState.obtenerSucursal();
                                this.setConsultaDelincuente(new ConsultarDelincuentesPorSucursal(suc));
                        }

                        List<PersonaDetenida> delincuentes = this.getDelincuentes();
                        if (delincuentes.isEmpty()) {
                            EntradaSalida.mostrarError("No existe el delincuente con los parametros seleccionados");
                            break;
                        } else {
                            for (PersonaDetenida delincuente : delincuentes) {
                                EntradaSalida.mostrarString(delincuente.getInfoPersonaDetenida());
                            }
                        }
                        break;
                    }
                case 4:
                    if (this.sistemaState.getJuicios().isEmpty()) {
                        EntradaSalida.mostrarError("Debe existir al menos un Juicio");
                        break;
                    }
                    List<Juicio> juicios = this.sistemaState.getJuicios();
                    if (juicios.isEmpty()) {
                        EntradaSalida.mostrarError("No hay juicios registrados");
                        break;

                    } else {
                        for (Juicio juicio : juicios) {
                            EntradaSalida.mostrarString(juicio.getInfoJuicio());
                        }
                    }
                    break;
                case 5:
                    if (this.sistemaState.getDelitos().isEmpty()) {
                        EntradaSalida.mostrarError("Debe existir al menos un delito");
                        break;
                    }
                    subMenu = Menu.mostrar("1-Mostrar Delitos con Condena, 2-Mostrar Delitos por Banda, 3-Mostrar Delitos por Delincuente", "Error.Reintente", 1, 3, 3);

                    if (subMenu == -1) {
                        EntradaSalida.mostrarError("Ingreso una opcion invalida");
                        break;

                    } else {
                        switch (subMenu) {
                            case 1:
                                this.setConsultaDelitos(new ConsultarDelitosConCondena(this.sistemaState.getDelitos()));
                                break;
                            case 2:
                                Banda b = this.sistemaState.obtenerBanda();
                                if (Objects.isNull(b)) {
                                    break;
                                }
                                this.setConsultaDelitos(new ConsultarDelitosPorBanda(this.sistemaState.getDelitos(), b));
                                break;

                            case 3:
                                PersonaDetenida unDelincuente = this.sistemaState.obtenerPersonaDetenida();
                                if (Objects.isNull(unDelincuente)) {
                                    break;
                                }
                                this.setConsultaDelitos(new ConsultarDelitosPorDelincuente(this.sistemaState.getDelitos(), unDelincuente));
                                break;
                        }

                        List<IDelito> delitos = this.getDelitos();
                        if (delitos.isEmpty()) {
                            EntradaSalida.mostrarError("No existen delitos con los parámetros seleccionados");
                        } else {
                            for (IDelito delito : delitos) {
                                EntradaSalida.mostrarString(delito.getInfoCompletaDelito());
                            }
                        }
                    }
                    break;
                case 6:

                    subMenu = Menu.mostrar("1-Mostrar Contratos por Banco, 2-Mostrar Contratos por Sucursal, 3-Mostrar Contratos por Vigilante", "Error.Reintente", 1, 3, 3);

                    if (subMenu == -1) {
                        EntradaSalida.mostrarError("Ingreso una opcion invalida");
                        break;

                    } else {
                        switch (subMenu) {

                            case 1:
                                bco = this.sistemaState.obtenerBanco();
                                if (Objects.isNull(bco)) {
                                    break;
                                }
                                this.setConsultaContrato(new ConsultarContratosBanco(bco));
                                break;

                            case 2:
                                Sucursal unaSucursal = this.sistemaState.obtenerSucursal();
                                if (Objects.isNull(unaSucursal)) {
                                    break;
                                }
                                if (unaSucursal.getContratos().isEmpty()) {
                                    EntradaSalida.mostrarError("Deben existir contratatos para la sucursal seleccionada");
                                    break;
                                }
                                this.setConsultaContrato(new ConsultarContratosSucursal(unaSucursal));
                                break;

                            case 3:
                                Vigilante unVigilante = this.sistemaState.obtenerVigilante();
                                if (Objects.isNull(unVigilante)) {
                                    EntradaSalida.mostrarError("Debe ingresar un Vigilante valido");
                                    break;
                                }
                                this.setConsultaContrato(new ConsultarContratosVigilante(unVigilante));
                                break;
                        }

                        List<Contrato> contratos = this.getContratos();
                        if (contratos.isEmpty()) {
                            EntradaSalida.mostrarError("No existen contratos con los parámetros seleccionados");
                            break;
                        } else {
                            for (Contrato contrato : contratos) {
                                EntradaSalida.mostrarString(contrato.getInfoContrato());
                            }
                        }
                    }
                    break;
                case 7:
                    Usuario user = this.crearUsuario();
                    this.sistemaState.addUsuario(user);
                    if (Objects.isNull(user)) {
                        EntradaSalida.mostrarError("Ocurrio un error crando el usuario");
                        break;
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
                    bco = new Banco(codigoBanco, domicilioBanco);
                    if (Objects.isNull(bco)) {
                        EntradaSalida.mostrarError("Ocurrio un error cargando el banco ");
                        break;
                    }
                    this.sistemaState.addBanco(bco);
                    break;
                case 9:
                    Banco unBanco = this.sistemaState.obtenerBanco();
                    if (Objects.isNull(unBanco)) {
                        codigoSucursal = EntradaSalida.leerString("Ingrese el codigo de la sucursal a asociar al banco");
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
                    IDelito delito = this.sistemaState.obtenerDelito();
                    Date fechaInicioCondena = EntradaSalida.leerDate("Ingrese fecha inicio condena");
                    Date fechaFinCondena = EntradaSalida.leerDate("Ingrese fecha fin condea");
                    IJuez juez = this.sistemaState.obtenerJuez();
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
                            personaDetenida = this.sistemaState.obtenerPersonaDetenida();
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
                        break;
                    }
                    boolean perteneceABanda = EntradaSalida.leerBoolean("¿El delincuente pertenece a una banda?");
                    if (perteneceABanda) {
                        Banda bandita = this.sistemaState.obtenerBanda();
                        bandita.addMiembro(delincuente);
                        this.sistemaState.actualizarBanda(bandita);
                    }
                    this.sistemaState.addDelincuente(delincuente);
                    break;
                case 13:
                    String codVig = EntradaSalida.leerString("Ingrese codigo del vigilante");
                    if (codVig.isEmpty()) {
                        EntradaSalida.mostrarError("El codigo no puede estar vacio");
                        break;
                    }
                    boolean esConArma = EntradaSalida.leerBoolean("¿Contrato con arma?");
                    Contrato contrato = this.sistemaState.obtenerContrato();
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
                        break;
                    }
                    this.sistemaState.addVigilante(vig);
                    break;
                case 14:
                    //Sucursal donde se carga el delito        
                    Sucursal suc = this.sistemaState.obtenerSucursal();
                    if (Objects.isNull(suc)) {
                        Date fechaDelito = EntradaSalida.leerDate("Ingrese la fecha del delito a buscar");
                        if (Objects.isNull(fechaDelito)) {
                            EntradaSalida.mostrarError("La fecha del delito no es correcta");
                            break;
                        }
                        PersonaDetenida personaD = this.sistemaState.obtenerPersonaDetenida();
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
                    Sucursal sucBcoCont = this.sistemaState.obtenerSucursal();
                    if (Objects.isNull(sucBcoCont)) {
                        EntradaSalida.mostrarError("La sucursal del banco no existe");
                        break;
                    }
                    Vigilante vigCon = this.sistemaState.obtenerVigilanteLibre();
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
                        break;
                    }
                    this.sistemaState.addJuez(unJuez);

                case 17: {
                    this.sistemaState.serializar("PoliciaFederal.bin");
                }
                case 18:
                    banda = this.sistemaState.obtenerBanda();
                    if (Objects.nonNull(banda)) {
                        do {
                            sigueCargando = EntradaSalida.leerBoolean("¿Desea cargar un miembro de la banda?");
                            if (sigueCargando) {
                                personaDetenida = this.sistemaState.obtenerPersonaDetenida();
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
                    break;
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
                    Vigilante vigilante = this.sistemaState.obtenerVigilante();
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

    public static Usuario nuevoUsuario(String u, String p, SistemaState sist) {
        UsuarioAdmin user = new UsuarioAdmin(sist);
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

    @Override
    public void setSistemaState(SistemaState s) {
        this.sistemaState = s;
    }
}
