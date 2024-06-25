package sistemapoliciafederal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioInvestigador extends Usuario implements IUsuarioConsultante {

    private SistemaState sistemaState;
    private IConsultaDelitos consultaDelitos;
    private IConsultaSucursal consultaSucursales;
    private IConsultaBanco consultaBanco;
    private IConsultaContrato consultaContrato;
    private IConsultaDelincuente consultaDelincuente;

    public UsuarioInvestigador(SistemaState sistemaState) {
    }

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-Mostrar Banco\n2-Mostrar Sucursales\n3-Mostrar Delincuentes\n4-Mostrar Juicios\n5-Mostrar Delitos\n6-Mostrar Contrato\n7-Mostrar vigilantes\n8-Mostrar informacion sistema\n9-Salir", "Error.Reintente", 1, 9, 3);
    }
    
    @Override
    public void accionar() {
        int subMenu;
        if (Objects.isNull(sistemaState)) {
            EntradaSalida.mostrarError("No existe informacion en el sistema");
        }

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
                    this.getInfoGeneral();
                    break;
                case 9:
                    continuarOperando = false;
                    break;
            }
            if (continuarOperando) {
                continuarOperando = EntradaSalida.leerBoolean("¿Desea continuar operando?");
            }
        } while (continuarOperando);

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
    public void setConsultaDelitos(IConsultaDelitos con
    ) {
        this.consultaDelitos = con;
    }

    @Override
    public List<IDelito> getDelitos() {
        return this.consultaDelitos.getDelitos();
    }

    @Override
    public void setConsultaSucursales(IConsultaSucursal con
    ) {
        this.consultaSucursales = con;
    }

    @Override
    public List<Sucursal> getSucursales() {
        return this.consultaSucursales.getSucursales();
    }

    @Override
    public void setConsultaBanco(IConsultaBanco con
    ) {
        this.consultaBanco = con;
    }

    @Override
    public Banco getBanco() {
        return this.consultaBanco.getBanco();
    }

    @Override
    public void setConsultaContrato(IConsultaContrato con
    ) {
        this.consultaContrato = con;
    }

    @Override
    public List<Contrato> getContratos() {
        return this.consultaContrato.getContratos();
    }

    @Override
    public void setConsultaDelincuente(IConsultaDelincuente con
    ) {
        this.consultaDelincuente = con;
    }

    @Override
    public List<PersonaDetenida> getDelincuentes() {
        return this.consultaDelincuente.getDelincuentes();
    }

    public static Usuario nuevoUsuario(String u, String p, SistemaState sist) {
        UsuarioInvestigador user = new UsuarioInvestigador(sist);
        user.setPass(p);
        user.setUser(u);
        return user;
    }

    @Override
    public void setSistemaState(SistemaState s) {
        this.sistemaState = s;
    }

}
