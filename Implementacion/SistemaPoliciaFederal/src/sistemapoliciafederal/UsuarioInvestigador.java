/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.IOException;
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
        return Menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato", "Error.Reintente", 1, 6, 3);
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

                default:
                    EntradaSalida.mostrarError("La opcion ingresada es incorrecta");
                    break;
            }
        } catch (Exception ex) {
            EntradaSalida.mostrarError(ex.getMessage());
        }
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
