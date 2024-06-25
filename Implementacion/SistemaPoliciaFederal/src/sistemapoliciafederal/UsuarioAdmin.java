package sistemapoliciafederal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return Menu.mostrar("1-Mostrar Banco\n2-Mostrar Sucursales\n3-Mostrar Delincuentes\n4-Mostrar Juicios\n5-Mostrar Delitos\n6-Mostrar Contrato\n7-Mostrar vigilantes\n8-Crear Usuario\n9-Cargar Banco\n10-Cargar Sucursal\n11-Cargar Juicio\n12-Carga Banda\n13-Cargar Delincuente\n14-Cargar Vigilante\n15-Cargar Delito\n16-Cargar Contrato\n17-Cargar Juez\n18-Guardar Sistema\n19-Agregar delincuente a banda\n20-Mostrar informacion sistema\n21-Salir", "Error.Reintente", 1, 21, 3);
    }

    @Override
    public void accionar() {
        int subMenu;
        if (Objects.isNull(sistemaState)) {
            EntradaSalida.mostrarError("No existe informacion en el sistema");
        }
        try {
            boolean continuarOperando;

            do {
                int opcionMenu = this.GetMenu();
                continuarOperando = true;

                switch (opcionMenu) {
                    case 1:
                        String domicilio;
                        String codigo;
                        if (this.sistemaState.getBancosSistema().isEmpty()) {
                            EntradaSalida.mostrarError("Debe existir al menos un banco");
                            break;

                        }
                        subMenu = Menu.mostrar("1-Mostrar banco por codigo\n2-Mostrar banco por domicilio\n3-Salir", "Error.Reintente", 1, 3, 3);

                        if (subMenu == -1) {
                            EntradaSalida.mostrarError("Ingreso una opcion invalida");
                            break;

                        } else {
                            switch (subMenu) {
                                case 1:
                                    codigo = EntradaSalida.leerString("Ingrese el codigo del banco a buscar");
                                    if (codigo.isEmpty()) {
                                        EntradaSalida.mostrarError("Debe ingresar un codigo valido");
                                    }
                                    this.setConsultaBanco(new ConsultarBancoPorCodigo(this.sistemaState.getBancosSistema(), codigo));
                                    break;
                                case 2:
                                    domicilio = EntradaSalida.leerString("Ingrese el domicilio del banco a buscar");
                                    if (domicilio.isEmpty()) {
                                        EntradaSalida.mostrarError("Debe ingresar un domicilio valido");
                                    }
                                    this.setConsultaBanco(new ConsultarBancoPorDomicilio(this.sistemaState.getBancosSistema(), domicilio));

                                    break;
                                case 3:
                                    continuarOperando = false;
                                    break;
                            }
                        }
                        if (!continuarOperando) {
                            continuarOperando = true;
                            break;
                        }
                        Banco unBanquito = this.getBanco();
                        if (Objects.isNull(unBanquito)) {
                            EntradaSalida.mostrarError("No existe el banco con los parametros seleccionados");
                        } else {
                            EntradaSalida.mostrarString(unBanquito.getInfoBanco());
                        }
                        break;

                    case 2:
                        String codigoSucursal;
                        Banco bco = this.sistemaState.obtenerBancoSistema();

                        if (Objects.isNull(bco)) {
                            EntradaSalida.mostrarError("Debe ingresar un banco valido");
                            break;
                        }
                        subMenu = Menu.mostrar("1-Mostrar Sucursales por Banco\n2-Mostrar Sucursal por Codigo\n3-Salir", "Error.Reintente", 1, 3, 3);

                        if (subMenu == -1) {
                            EntradaSalida.mostrarError("Ingreso una opcion invalida");

                        } else {
                            switch (subMenu) {
                                case 1:
                                    this.setConsultaSucursales(new ConsultarSucursalesPorBanco(bco));
                                    break;
                                case 2:
                                    codigoSucursal = EntradaSalida.leerString("Ingrese el codigo de la sucursal a buscar");
                                    if (codigoSucursal.isEmpty()) {
                                        EntradaSalida.mostrarError("Debe ingresar un codigo de sucursal valido");
                                        break;
                                    }
                                    this.setConsultaSucursales(new ConsultarSucursalPorCodigo(bco.getSucursales(), codigoSucursal));
                                    break;
                                case 3:
                                    continuarOperando = false;
                                    break;
                            }
                            if (!continuarOperando) {
                                continuarOperando = true;
                                break;
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
                        break;
                    case 3:
                        if (!this.sistemaState.getDelincuentesSistema().isEmpty()) {
                            EntradaSalida.mostrarError("Debe existir al menos un delincuente");
                        }
                        subMenu = Menu.mostrar("1-Mostrar Delincuente por Nombre\n2-Mostrar Delincuente por Codigo\n3-Mostrar Delincuente por Sucursal\n4-Salir", "Error.Reintente", 1, 4, 3);

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
                                    this.setConsultaDelincuente(new ConsultarDelincuentePorNombre(this.sistemaState.getDelincuentesSistema(), nombre));

                                    break;
                                case 2:
                                    String codigoDelincuente = EntradaSalida.leerString("Ingrese el codigo del delincuente a buscar");
                                    if (codigoDelincuente.isEmpty()) {
                                        EntradaSalida.mostrarError("Debe ingresar un Codigo de Delincuente valido");
                                        break;
                                    }
                                    this.setConsultaDelincuente(new ConsultarDelincuentesPorCodigo(this.sistemaState.getDelincuentesSistema(), codigoDelincuente));

                                    break;
                                case 3:
                                    Sucursal suc = this.sistemaState.obtenerSucursalSistema();
                                    this.setConsultaDelincuente(new ConsultarDelincuentesPorSucursal(suc));
                                    break;
                                case 4:
                                    continuarOperando = false;
                                    break;
                            }
                            if (!continuarOperando) {
                                continuarOperando = true;
                                break;
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
                        break;
                    case 4:
                        List<Juicio> juicios = this.sistemaState.getJuiciosSistema();
                        if (juicios.isEmpty()) {
                            EntradaSalida.mostrarError("No hay juicios registrados");
                            break;
                        }
                        for (Juicio juicio : juicios) {
                            EntradaSalida.mostrarString(juicio.getInfoJuicio());
                        }
                        break;
                    case 5:
                        if (this.sistemaState.getDelitosSistema().isEmpty()) {
                            EntradaSalida.mostrarError("Debe existir al menos un delito");
                            break;
                        }
                        subMenu = Menu.mostrar("1-Mostrar Delitos con Condena\n2-Mostrar Delitos por Banda\n3-Mostrar Delitos por Delincuente\n4-Salir", "Error.Reintente", 1, 4, 3);

                        if (subMenu == -1) {
                            EntradaSalida.mostrarError("Ingreso una opcion invalida");
                            break;

                        } else {
                            switch (subMenu) {
                                case 1:
                                    this.setConsultaDelitos(new ConsultarDelitosConCondena(this.sistemaState.getDelitosSistema()));
                                    break;

                                case 2:
                                    Banda b = this.sistemaState.obtenerBandaSistema();
                                    if (Objects.isNull(b)) {
                                        break;
                                    }
                                    this.setConsultaDelitos(new ConsultarDelitosPorBanda(this.sistemaState.getDelitosSistema(), b));

                                    break;
                                case 3:
                                    PersonaDetenida unDelincuente = this.sistemaState.obtenerPersonaDetenidaSistema();
                                    if (Objects.isNull(unDelincuente)) {
                                        break;
                                    }
                                    this.setConsultaDelitos(new ConsultarDelitosPorDelincuente(this.sistemaState.getDelitosSistema(), unDelincuente));
                                    break;
                                case 4:
                                    continuarOperando = false;
                                    break;
                            }
                            if (!continuarOperando) {
                                continuarOperando = true;
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
                        subMenu = Menu.mostrar("1-Mostrar Contratos por Banco\n2-Mostrar Contratos por Sucursal\n3-Mostrar Contratos por Vigilante\n4-Salir", "Error.Reintente", 1, 4, 3);
                        if (subMenu == -1) {
                            EntradaSalida.mostrarError("Ingreso una opcion invalida");
                            break;

                        } else {
                            switch (subMenu) {

                                case 1:
                                    bco = this.sistemaState.obtenerBancoSistema();
                                    if (Objects.isNull(bco)) {
                                        break;
                                    }
                                    this.setConsultaContrato(new ConsultarContratosBanco(bco));
                                    break;
                                case 2:
                                    Sucursal unaSucursal = this.sistemaState.obtenerSucursalSistema();
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
                                    Vigilante unVigilante = this.sistemaState.obtenerVigilanteSistema();
                                    if (Objects.isNull(unVigilante)) {
                                        EntradaSalida.mostrarError("Debe ingresar un Vigilante valido");
                                        break;
                                    }
                                    this.setConsultaContrato(new ConsultarContratosVigilante(unVigilante));
                                    break;
                                case 4:
                                    continuarOperando = false;
                                    break;
                            }
                            if (!continuarOperando) {
                                continuarOperando = true;
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
                        subMenu = Menu.mostrar("1-Mostrar Vigilantes sistema\n2-Salir", "Error.Reintente", 1, 3, 3);
                        List<Vigilante> vigilantesList = new ArrayList<>();
                        switch (subMenu) {
                            case 1:
                                vigilantesList = this.sistemaState.getVigilantesSistema();
                                break;
                            case 2:
                                continuarOperando = false;
                                break;
                        }
                        if (!continuarOperando) {
                            break;
                        }
                        for (Vigilante v : vigilantesList) {
                            EntradaSalida.mostrarString(v.getInfoVigilante());
                        }
                        break;

                    case 8:
                        Usuario user = this.crearUsuario();
                        this.sistemaState.addUsuarioSistema(user);
                        if (Objects.isNull(user)) {
                            EntradaSalida.mostrarError("Ocurrio un error crando el usuario");
                            break;
                        }
                        break;
                    case 9:
                        continuarOperando = EntradaSalida.leerBoolean("¿Desea volver al menu principal?");
                        if (continuarOperando) {
                            break;
                        } else {
                            continuarOperando = true;
                        }

                        String codigoBanco = EntradaSalida.leerString("Ingrese el codigo del banco");
                        if (codigoBanco.isEmpty()) {
                            EntradaSalida.mostrarError("El codigo de banco no puede estar vacio");
                            break;
                        }

                        Banco baux = this.sistemaState.obtenerBancoSistema(codigoBanco);
                        if (Objects.nonNull(baux)) {
                            EntradaSalida.mostrarError("El codigo del banco ya se encuentra asociado a otro ya cargado en el sistema");
                            break;
                        }

                        String domicilioBanco = EntradaSalida.leerString("Ingrese el domicilio del banco");
                        if (domicilioBanco.isEmpty()) {
                            EntradaSalida.mostrarError("El domicilio no puede estar vacio");
                            break;
                        }

                        bco = new Banco(codigoBanco, domicilioBanco);
                        if (Objects.isNull(bco)) {
                            EntradaSalida.mostrarError("No se puede obtener el banco ");
                            break;
                        }
                        this.sistemaState.addBancoSistema(bco);
                        EntradaSalida.mostrarString("Banco creado con exito");
                        break;
                    case 10:
                        Banco unBanco = this.sistemaState.obtenerBancoSistema();
                        if (Objects.isNull(unBanco)) {
                            EntradaSalida.mostrarError("Ocurrio un error cargando el banco ");
                            break;
                        }
                        continuarOperando = EntradaSalida.leerBoolean("¿Desea volver al menu principal?");

                        if (continuarOperando) {
                            break;
                        } else {
                            continuarOperando = true;
                        }
                        codigoSucursal = EntradaSalida.leerString("Ingrese el codigo de la sucursal a asociar al banco");
                        if (codigoSucursal.isEmpty()) {
                            EntradaSalida.mostrarError("El codigo de la sucursal no puede estar vacio");
                            break;
                        }

                        Sucursal saux = this.sistemaState.obtenerSucursalSistema(codigoSucursal, unBanco);
                        if (Objects.nonNull(saux)) {
                            EntradaSalida.mostrarError("El codigo de la sucursal del banco ya se encuentra asociado a otra ya cargado en el sistema");
                            break;
                        }

                        String domicilioSucursal = EntradaSalida.leerString("Ingrese el domicilio de la sucursal a asociar al banco");
                        if (domicilioSucursal.isEmpty()) {
                            EntradaSalida.mostrarError("El domicilio de la sucursal no puede estar vacio");
                            break;
                        }

                        int cantEmpleados = EntradaSalida.leerEntero("Ingrese la cantidad de empleados de la sucursal");
                        if (cantEmpleados <= 0) {
                            EntradaSalida.mostrarError("El nro de empleados no puede ser 0");
                            break;
                        }

                        unBanco.addSucursal(codigoSucursal, domicilioSucursal, cantEmpleados);
                        if (Objects.isNull(unBanco)) {
                            EntradaSalida.mostrarError("Ocurrio un error cargando la sucursal del banco");
                            break;
                        }
                        this.sistemaState.actualizarBancoSistema(unBanco);
                        EntradaSalida.mostrarString("Sucursal creada con exito");
                        break;
                    case 11:
                        Date fechaJuicio = EntradaSalida.leerDate("Ingrese fecha del juicio");
                        IDelito delito = this.sistemaState.obtenerDelitoSistema();
                        if (Objects.isNull(delito)) {
                            EntradaSalida.mostrarError("El delito ingresado no existe");
                            break;
                        }
                        Date fechaInicioCondena = EntradaSalida.leerDate("Ingrese fecha inicio condena");
                        Date fechaFinCondena = EntradaSalida.leerDate("Ingrese fecha fin condea");
                        IJuez juez = this.sistemaState.obtenerJuez();

                        Juicio juicio = new Juicio(juez, fechaJuicio, delito, fechaInicioCondena, fechaFinCondena);
                        if (Objects.isNull(juicio)) {
                            EntradaSalida.mostrarError("Ocurrio un error cargando el juez");
                            break;
                        }
                        this.sistemaState.addJuicioSistema(juicio);
                        EntradaSalida.mostrarString("Juicio creado con exito");
                        break;

                    case 12:
                        PersonaDetenida personaDetenida;
                        boolean sigueCargando;

                        int codigoBanda = EntradaSalida.leerEntero("Ingrese el numero de la banda a cargar");
                        if (codigoBanda <= 0) {
                            EntradaSalida.mostrarError("El codigo de la banda no puede ser 0");
                            break;
                        }

                        Banda banda = this.sistemaState.obtenerBandaSistema(codigoBanda);
                        if (Objects.nonNull(banda)) {
                            EntradaSalida.mostrarError("El codigo de banda ya pertence a otra banda ya cargada");
                            break;
                        }
                        banda = new Banda(codigoBanda);
                        if (Objects.isNull(banda)) {
                            EntradaSalida.mostrarError("Ocurrio un error cargando la banda");
                            break;
                        }

                        do {
                            sigueCargando = EntradaSalida.leerBoolean("¿Desea cargar un miembro de la banda? \n Este miembro ya debe estar cargado previamente en la Opcion del Menu - Cargar Delincuente-.");
                            if (sigueCargando) {
                                personaDetenida = this.sistemaState.obtenerPersonaDetenidaSistema();
                                if (Objects.isNull(personaDetenida)) {
                                    EntradaSalida.mostrarError("El delincuente no existe");
                                    break;
                                } else {
                                    banda.addMiembro(personaDetenida);
                                    EntradaSalida.mostrarString("Se agregó un nuevo miembro.");
                                }
                            }

                        } while (sigueCargando);

                        this.sistemaState.actualizarBandaSistema(banda);
                        EntradaSalida.mostrarString("Banda creada con exito");
                        break;

                    case 13:
                        String codigoDelincuente = EntradaSalida.leerString("Ingrese el codigo del delincuente a cargar");
                        if (codigoDelincuente.isEmpty()) {
                            EntradaSalida.mostrarError("El codigo del delincuente no puede estar vacio");
                            break;
                        }
                        String nombreDelicuente = EntradaSalida.leerString("Ingrese el nombre del delincuente a cargar");
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
                            Banda bandita = this.sistemaState.obtenerBandaSistema();
                            if (Objects.isNull(bandita)) {
                                break;
                            }
                            bandita.addMiembro(delincuente);
                            this.sistemaState.actualizarBandaSistema(bandita);
                        }
                        this.sistemaState.addDelincuenteSistema(delincuente);
                        EntradaSalida.mostrarString("Delincuente creado con exito");
                        break;

                    case 14:
                        continuarOperando = EntradaSalida.leerBoolean("¿Desea volver al menu principal?");

                        if (continuarOperando) {
                            break;
                        } else {
                            continuarOperando = true;
                        }
                        String codVig = EntradaSalida.leerString("Ingrese codigo del vigilante");
                        if (codVig.isEmpty()) {
                            EntradaSalida.mostrarError("El codigo no puede estar vacio");
                            break;
                        }
                        Vigilante vaux = this.sistemaState.obtenerVigilanteSistema(codVig);
                        if (Objects.nonNull(vaux)) {
                            EntradaSalida.mostrarError("El codigo de vigilante ya se encuentra asociado a otro ya cargado en el sistema");
                            break;
                        }

                        boolean esConArma = EntradaSalida.leerBoolean("¿Utiliza arma el vigilante?");
                        boolean conContrato = EntradaSalida.leerBoolean("¿Se le asocia un contrato?");

                        Contrato contrato = null;
                        if (conContrato) {
                            contrato = this.sistemaState.obtenerContratoLibreSistema();
                            if (Objects.isNull(contrato)) {
                                EntradaSalida.mostrarError("No se encontro un contrato para asociar");
                                break;
                            }
                        }

                        int edad = EntradaSalida.leerEntero("Ingrese la edad");
                        if (edad <= 0) {
                            EntradaSalida.mostrarError("La edad no puede ser 0");
                            break;
                        }

                        Vigilante vig = new Vigilante(codVig, edad, esConArma, contrato);
                        if (Objects.isNull(vig)) {
                            EntradaSalida.mostrarError("Ocurrio un error cargado el vigilante");
                            break;
                        }
                        this.sistemaState.addVigilanteSistema(vig);
                        EntradaSalida.mostrarString("Vigilante creado con exito");
                        break;
                    case 15:
                        continuarOperando = EntradaSalida.leerBoolean("¿Desea volver al menu principal?");

                        if (continuarOperando) {
                            break;
                        } else {
                            continuarOperando = true;
                        }
                        Sucursal suc = this.sistemaState.obtenerSucursalSistema();
                        if (Objects.isNull(suc)) {
                            break;
                        }
                        Date fechaDelito = EntradaSalida.leerDate("Ingrese la fecha del delito a buscar");
                        if (Objects.isNull(fechaDelito)) {
                            EntradaSalida.mostrarError("La fecha del delito no es correcta");
                            break;
                        }

                        PersonaDetenida personaD = this.sistemaState.obtenerPersonaDetenidaSistema();
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
                        this.sistemaState.addDelitoSistema(d);
                        suc.cargarDelito(d);
                        this.sistemaState.actualizarBancoSistema(suc);
                        EntradaSalida.mostrarString("Delito creado con exito");

                        break;
                    case 16:
                        continuarOperando = EntradaSalida.leerBoolean("¿Desea volver al menu principal?");

                        if (continuarOperando) {
                            break;
                        } else {
                            continuarOperando = true;
                        }
                        Date fechaContrato = EntradaSalida.leerDate("Ingrese la fecha del contrato");
                        if (Objects.isNull(fechaContrato)) {
                            EntradaSalida.mostrarError("Fecha incorrecta");
                            break;
                        }
                        Sucursal sucBcoCont = this.sistemaState.obtenerSucursalSistema();
                        if (Objects.isNull(sucBcoCont)) {
                            EntradaSalida.mostrarError("La sucursal del banco no existe");
                            continuarOperando = true;
                            break;
                        }
                        Vigilante vigCon = this.sistemaState.obtenerVigilanteLibreSistema();
                        if (Objects.isNull(vigCon)) {
                            break;
                        }
                        boolean esConArmaCont = EntradaSalida.leerBoolean("¿Contrato con arma?");
                        sucBcoCont.GenerarContrato(esConArmaCont, fechaContrato, vigCon);
                        this.sistemaState.actualizarBancoSistema(sucBcoCont);
                        EntradaSalida.mostrarString("Contrato creado con exito");
                        break;
                    case 17:
                        continuarOperando = EntradaSalida.leerBoolean("¿Desea volver al menu principal?");

                        if (continuarOperando) {
                            break;
                        } else {
                            continuarOperando = true;
                        }
                        String claveInternaJuzgado = EntradaSalida.leerString("Ingrese la clave interna del juzgado");
                        if (claveInternaJuzgado.isEmpty()) {
                            EntradaSalida.mostrarError("La clave interna del juzgado no puede ser vacia");
                            break;
                        }
                        IJuez jaux = this.sistemaState.obtenerJuezSistema(claveInternaJuzgado);
                        if (Objects.nonNull(jaux)) {
                            EntradaSalida.mostrarError("La clave interna del juzgado ya se encuentra asociada a otro juez ya cargado en el sistema");
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
                        this.sistemaState.addJuezSistema(unJuez);
                        EntradaSalida.mostrarString("Juez creado con exito");
                        break;
                    case 18:
                        this.sistemaState.serializar("PoliciaFederal.dat");
                        EntradaSalida.mostrarString("Salvado del sistema realizado con exito");
                        break;
                    case 19:
                        banda = this.sistemaState.obtenerBandaSistema();
                        if (Objects.nonNull(banda)) {
                            do {
                                sigueCargando = EntradaSalida.leerBoolean("¿Desea cargar un miembro de la banda?\n Este miembro ya debe estar cargado previamente en la Opcion del Menu - Cargar Delincuente-.");
                                if (sigueCargando) {
                                    personaDetenida = this.sistemaState.obtenerPersonaDetenidaSistema();
                                    if (Objects.isNull(personaDetenida)) {
                                        EntradaSalida.mostrarError("El delincuente no existe");
                                        break;
                                    } else {
                                        banda.addMiembro(personaDetenida);
                                        EntradaSalida.mostrarString("Se agregó un nuevo miembro.");
                                    }
                                }
                            } while (sigueCargando);
                        }
                        break;

                    case 20:
                        this.getInfoGeneral();
                        break;
                    case 21:
                        continuarOperando = false;
                        break;
                }
                if (continuarOperando) {
                    continuarOperando = EntradaSalida.leerBoolean("¿Desea continuar operando?");
                }
            } while (continuarOperando);
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
            String usuario = EntradaSalida.leerString("Ingrese el nombre del usuario");
            String pass = EntradaSalida.leerPassword("Ingrese la contraseña del usuario");

            if (!this.sistemaState.getUsuariosSistema().stream().filter(u -> u.getUser().equals(usuario)).collect(Collectors.toList()).isEmpty()) {
                EntradaSalida.mostrarError("El nombre de usuario ya se encuentra asociado a otro usuario");
                return null;
            }
            switch (tipoUsuario) {
                case 1:
                    user = UsuarioAdmin.nuevoUsuario(usuario, pass, sistemaState);
                    break;
                case 2:
                    user = UsuarioInvestigador.nuevoUsuario(usuario, pass, sistemaState);
                    break;
                case 3:
                    Vigilante vigilante = this.sistemaState.obtenerVigilanteSistema();
                    if (Objects.nonNull(vigilante)) {
                        user = UsuarioVigilante.nuevoUsuario(usuario, pass);
                        ((UsuarioVigilante) user).setVigilanteAsociado(vigilante);
                    }
                    break;
            }
        }
        if (Objects.isNull(user)) {
            EntradaSalida.mostrarError("Error creando el usuario");
            return user;
        }
        EntradaSalida.mostrarString("Usuario creado con exito");
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
        EntradaSalida.mostrarString("Bancos:");
        for (Banco banco : sistemaState.getBancosSistema()) {
            EntradaSalida.mostrarString(banco.getInfoBanco());
        }

        EntradaSalida.mostrarString("\nDelitos:");
        for (IDelito delito : sistemaState.getDelitosSistema()) {
            EntradaSalida.mostrarString(delito.getInfoCompletaDelito());
        }

        EntradaSalida.mostrarString("\nDelincuentes:");
        for (PersonaDetenida delincuente : sistemaState.getDelincuentesSistema()) {
            EntradaSalida.mostrarString(delincuente.getInfoPersonaDetenida());
        }

        EntradaSalida.mostrarString("\nJuicios:");
        for (Juicio juicio : sistemaState.getJuiciosSistema()) {
            EntradaSalida.mostrarString(juicio.getInfoJuicio());
        }

        EntradaSalida.mostrarString("\nVigilantes:");
        for (Vigilante vigilante : sistemaState.getVigilantesSistema()) {
            EntradaSalida.mostrarString(vigilante.getInfoVigilante());
        }

        EntradaSalida.mostrarString("\nJueces:");
        for (Juez juez : sistemaState.getJuecesSistema()) {
            EntradaSalida.mostrarString(juez.getInfoJuez());
        }

        EntradaSalida.mostrarString("\nBandas:");
        for (Banda banda : sistemaState.getBandasSistema()) {
            EntradaSalida.mostrarString(banda.getInfoBanda());
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
