package sistemapoliciafederal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class SistemaState implements Serializable {

    private List<Juicio> juicios;
    private List<Juez> jueces;
    private List<Vigilante> vigilantes;
    private List<Banco> bancos;
    private List<IDelito> delitos;
    private List<Banda> bandas;
    private List<Usuario> usuarios;
    private List<PersonaDetenida> delincuentes;
    private static SistemaState instancia;

    private SistemaState() {
        juicios = new ArrayList<>();
        jueces = new ArrayList<>();
        vigilantes = new ArrayList<>();
        bancos = new ArrayList<>();
        delitos = new ArrayList<>();
        bandas = new ArrayList<>();
        usuarios = new ArrayList<>();
        delincuentes = new ArrayList<>();
    }

    //SINGLETON
    public static SistemaState newSistemaState() {

        if (Objects.isNull(instancia)) {
            instancia = new SistemaState();
        }
        return instancia;
    }

    public SistemaState deSerializar(String a) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(a);
        ObjectInputStream o = new ObjectInputStream(f);
        SistemaState s = (SistemaState) o.readObject();
        o.close();
        f.close();
        return s;
    }

    public void serializar(String a) throws IOException {
        FileOutputStream f = new FileOutputStream(a);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

    public List<Juicio> getJuiciosSistema() {
        return juicios;
    }

    public List<Juez> getJuecesSistema() {
        return jueces;
    }

    public List<Vigilante> getVigilantesSistema() {
        return vigilantes;
    }

    public List<Banco> getBancosSistema() {
        return bancos;
    }

    public List<Usuario> getUsuariosSistema() {
        return usuarios;
    }

    public List<Banda> getBandasSistema() {
        return bandas;
    }

    public void addJuicioSistema(Juicio j) {
        this.juicios.add(j);
    }

    public void addUsuarioSistema(Usuario u) {
        this.usuarios.add(u);
    }

    public void addUsuarioSistema(List<Usuario> users) {
        //Filtro los usuario de la lista que me llega que no existan ya.
        List<Usuario> usuariosNuevos = this.usuarios.stream().filter(u -> users.contains(u)).collect(Collectors.toList());

        for (Usuario u : usuariosNuevos) {

            this.usuarios.add(u);
        }

    }

    public void addJuezSistema(Juez j) {
        this.jueces.add(j);
    }

    public void addVigilanteSistema(Vigilante j) {
        this.vigilantes.add(j);
    }

    public void addBancoSistema(Banco j) {
        this.bancos.add(j);
    }

    public Banco obtenerBancoSistema(Sucursal suc) {
        for (Banco b : this.getBancosSistema()) {
            for (Sucursal s : b.getSucursales()) {
                if (s.equals(suc)) {
                    return b;
                }
            }
        }
        EntradaSalida.mostrarError("Banco no encontrado");
        return null;
    }

    public void actualizarBancoSistema(Sucursal s) {
        Banco b = this.obtenerBancoSistema(s);
        this.actualizarBancoSistema(b);
    }

    public void actualizarBancoSistema(Banco b) {
        this.bancos.remove(b);
        this.bancos.add(b);
    }

    public List<IDelito> getDelitosSistema() {
        return delitos;
    }

    public void addDelitoSistema(Delito delito) {
        this.delitos.add(delito);
    }

    public void actualizarBandaSistema(Banda b) {
        this.bandas.remove(b);
        this.bandas.add(b);

    }

    public void addBandaSistema(Banda j) {
        this.bandas.add(j);
    }

    public void setJuiciosSistema(List<Juicio> juicios) {
        this.juicios = juicios;
    }

    public void setJuecesSistema(List<Juez> jueces) {
        this.jueces = jueces;
    }

    public void setVigilantesSistema(List<Vigilante> vigilantes) {
        this.vigilantes = vigilantes;
    }

    public void setBancosSistema(List<Banco> bancos) {
        this.bancos = bancos;
    }

    public void setDelitosSistema(List<IDelito> delitos) {
        this.delitos = delitos;
    }

    public void setBandasSistema(List<Banda> bandas) {
        this.bandas = bandas;
    }

    public void setUsuariosSistema(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<PersonaDetenida> getDelincuentesSistema() {
        return delincuentes;
    }

    public void setDelincuentesSistema(List<PersonaDetenida> delincuentes) {
        this.delincuentes = delincuentes;
    }

    public void addDelincuenteSistema(PersonaDetenida delincuente) {
        this.delincuentes.add(delincuente);
    }

    public Vigilante obtenerVigilanteLibreSistema() {
        Vigilante v = null;

        for (Vigilante vig : this.getVigilantesSistema()) {
            if (Objects.isNull(vig.getContrato())) {
                return vig;
            }
        }
        EntradaSalida.mostrarError("No se encuentra un vigilante libre");
        return v;
    }

    public Vigilante obtenerVigilanteSistema() {
        Vigilante v = null;
        int cantidadDeReintentos = 3;
        boolean sigue;
        do {
            String codigo = EntradaSalida.leerString("Ingrese el codigo del vigilante a buscar");

            if (codigo.isEmpty()) {
                EntradaSalida.mostrarError("El codigo del vigilante no puede ser vacio");
                cantidadDeReintentos--;
                break;
            }
            for (Vigilante vig : this.getVigilantesSistema()) {
                if (vig.soyElVigilante(codigo)) {
                    return vig;
                }
            }
            sigue = EntradaSalida.leerBoolean("Vigilante no encontrado. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        EntradaSalida.mostrarError("No se encuentra el vigilante para el codigo ingresado");
        return v;
    }

    public Vigilante obtenerVigilanteSistema(String codigo) {
        Vigilante v = null;
        for (Vigilante vig : this.getVigilantesSistema()) {
            if (vig.soyElVigilante(codigo)) {
                return vig;
            }
        }
        return v;
    }

    public IDelito obtenerDelitoSistema() {
        Delito d = null;
        Date fechaDelito = EntradaSalida.leerDate("Ingrese la fecha del delito a buscar");
        if (Objects.isNull(fechaDelito)) {
            EntradaSalida.mostrarError("La fecha del delito no es correcta");
            return d;
        }
        PersonaDetenida personaD = obtenerPersonaDetenidaSistema();
        if (Objects.isNull(personaD)) {
            EntradaSalida.mostrarError("La persona detenida no existe");
            return d;
        }
        boolean condena = EntradaSalida.leerBoolean("¿El delito es con condena?");

        for (IDelito dd : this.getDelitosSistema()) {
            if (((Delito) dd).soyElDelito(fechaDelito, personaD, condena)) {
                return ((Delito) dd);
            }
        }

        return d;
    }

    public List<Contrato> obtenerContratoSinVigilanteSistema() {
        boolean datosValidos;
        datosValidos = true;
        Sucursal suc = this.obtenerSucursalSistema();
        if (Objects.isNull(suc)) {
            EntradaSalida.mostrarError("La sucursal del banco no existe");
            datosValidos = false;
        }
        List<Contrato> contratoLibres = suc.getContratos();

        if (datosValidos) {
            for (Contrato c : suc.getContratos()) {
                //si el contrato pertenece a algun vigilante que me lo agregue a la coleccion de contratos usados
                if (this.getVigilantesSistema().stream().allMatch(v -> v.getContrato().getFechaContrato().equals(c.getFechaContrato()))) {
                    contratoLibres.remove(c);
                }
            }
        }
        return contratoLibres;
    }

    public Contrato obtenerContratoLibreSistema() {
        Contrato contrato = null;
        int cantidadDeReintentos = 3;
        boolean sigue;
        do {
            Date fechaContrato = EntradaSalida.leerDate("Ingrese la fecha del contrato");
            if (Objects.isNull(fechaContrato)) {
                EntradaSalida.mostrarError("Fecha incorrecta");
                cantidadDeReintentos--;
                break;
            }

            boolean esConArma = EntradaSalida.leerBoolean("¿Contrato con arma?");

            for (Contrato c : this.obtenerContratoSinVigilanteSistema()) {
                if (c.soyElContrato(fechaContrato, esConArma)) {
                    return c;
                }

            }
            sigue = EntradaSalida.leerBoolean("Contrato no encontrado. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        return contrato;

    }

    public Contrato obtenerContratoSistema() {
        Contrato contrato = null;
        int cantidadDeReintentos = 3;
        boolean sigue;

        do {
            Date fechaContrato = EntradaSalida.leerDate("Ingrese la fecha del contrato");
            if (Objects.isNull(fechaContrato)) {
                EntradaSalida.mostrarError("Fecha incorrecta");
                cantidadDeReintentos--;
                break;
            }
            Sucursal suc = this.obtenerSucursalSistema();
            if (Objects.isNull(suc)) {
                EntradaSalida.mostrarError("La sucursal del banco no existe");
                cantidadDeReintentos--;
                break;
            }
            boolean esConArma = EntradaSalida.leerBoolean("¿Contrato con arma?");

            for (Contrato c : suc.getContratos()) {
                if (c.soyElContrato(fechaContrato, esConArma)) {
                    return c;
                }
            }

            sigue = EntradaSalida.leerBoolean("Contrato no encontrado. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        return contrato;

    }

    public IJuez obtenerJuez() {
        IJuez juez = null;
        int cantidadDeReintentos = 3;
        boolean sigue;

        do {
            String codigoInternaJuzgado = EntradaSalida.leerString("Ingrese la clave interna del juzgado");

            if (codigoInternaJuzgado.isEmpty()) {
                EntradaSalida.mostrarError("La clave ingresada esta vacia");
                cantidadDeReintentos--;
                break;
            }
            for (IJuez j : this.getJuecesSistema()) {
                if (((Juez) j).soyElJuez(codigoInternaJuzgado)) {
                    return j;
                }
            }
            sigue = EntradaSalida.leerBoolean("Juez no encontrado. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        EntradaSalida.mostrarError("El codigo ingresado no pertenece a un juez");
        return juez;
    }

    public IJuez obtenerJuezSistema(String codigoInternaJuzgado) {
        IJuez juez = null;
        for (IJuez j : this.getJuecesSistema()) {
            if (((Juez) j).soyElJuez(codigoInternaJuzgado)) {
                return j;
            }
        }
        return juez;
    }

    public PersonaDetenida obtenerPersonaDetenidaSistema() {
        PersonaDetenida p = null;
        int cantidadDeReintentos = 3;
        boolean sigue;
        do {
            String codigoPersona = EntradaSalida.leerString("Ingrese el codigo del delincuente");
            if (codigoPersona.isEmpty()) {
                EntradaSalida.mostrarError("El codigo ingresado no puede ser vacio");
                cantidadDeReintentos--;
                break;

            }
            for (PersonaDetenida pd : this.getDelincuentesSistema()) {
                if (pd.soyElDelincuente(codigoPersona)) {
                    return pd;
                }
            }
            sigue = EntradaSalida.leerBoolean("Delincuente no encontrada. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        EntradaSalida.mostrarError("El codigo ingresado no pertenece a un delincuente");
        return p;
    }

    public List<Sucursal> obtenerSucursalesSistema() {
        Banco banco = this.obtenerBancoSistema();
        if (Objects.isNull(banco)) {
            EntradaSalida.mostrarError("El banco no existe");
            return null;
        }
        return banco.getSucursales();

    }

    public Sucursal obtenerSucursalSistema(String codigoSucursal, Banco banco) {
        Sucursal sucursal = null;
        for (Sucursal suc : banco.getSucursales()) {
            if (suc.soyLaSucursal(codigoSucursal)) {
                return suc;
            }
        }
        return sucursal;
    }

    public Sucursal obtenerSucursalSistema() {
        int cantidadDeReintentos = 3;
        boolean sigue;
        Sucursal sucursal = null;
        do {

            Banco banco = this.obtenerBancoSistema();
            if (Objects.isNull(banco)) {
                EntradaSalida.mostrarError("El banco no existe");
                cantidadDeReintentos--;
                break;
            }
            String codigo = EntradaSalida.leerString("Ingrese el codigo de la sucursal.\n La sucursal debe estar cargada previamente en la opción del menú -Cargar Sucursal-");
            if (codigo.isEmpty()) {
                EntradaSalida.mostrarError("El codigo  no puede ser vacio");
                cantidadDeReintentos--;
                break;
            }
            for (Sucursal suc : banco.getSucursales()) {
                if (suc.soyLaSucursal(codigo)) {
                    return suc;
                }
            }
            sigue = EntradaSalida.leerBoolean("Sucursal no encontrada. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        EntradaSalida.mostrarError("No se encuentra la sucursal para el codigo ingresado");
        return sucursal;
    }

    public Banco obtenerBancoSistema(String codigo) {
        Banco bco = null;

        for (Banco banco : this.getBancosSistema()) {
            if (banco.soyElBanco(codigo)) {
                return banco;
            }
        }
        return bco;
    }

    public Banco obtenerBancoSistema() {
        Banco bco = null;
        int cantidadDeReintentos = 3;
        boolean sigue;
        do {
            String codigo = EntradaSalida.leerString("Ingrese el codigo del banco a buscar.\nEl banco debe estar cargado previamente en la opcion de menu -Cargar Banco-");
            if (codigo.isEmpty()) {
                EntradaSalida.mostrarError("El codigo del banco no puede ser vacio");
                cantidadDeReintentos--;
                break;
            }
            for (Banco banco : this.getBancosSistema()) {
                if (banco.soyElBanco(codigo)) {
                    return banco;
                }
            }
            sigue = EntradaSalida.leerBoolean("Banco no encontrado. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        EntradaSalida.mostrarError("No se encuentra el banco para el codigo ingresado");
        return bco;
    }

    public Banda obtenerBandaSistema() {
        int cantidadDeReintentos = 3;
        boolean sigue;
        do {
            int nroBanda = EntradaSalida.leerEntero("Ingrese el nro de la banda\nLa banda ya debe estar cargada previamente en la Opcion del Menu -Cargar Banda-");
            if (nroBanda <= 0) {
                EntradaSalida.mostrarError("El nro de banda no puede ser 0");
                cantidadDeReintentos--;
                break;
            }
            for (Banda b : this.getBandasSistema()) {
                if (b.soyLaBanda(nroBanda)) {
                    return b;
                }
            }

            sigue = EntradaSalida.leerBoolean("Banda no encontrada. ¿Desea volver a intentar las busqueda? ");
            if (sigue) {
                EntradaSalida.mostrarString("Cantidad de intentos restantes para reintentar la busqueda:" + cantidadDeReintentos);
                cantidadDeReintentos--;
            }
        } while (sigue && cantidadDeReintentos > 0);
        EntradaSalida.mostrarError("No se encontro ninguna banda");

        return null;

    }

    public Banda obtenerBandaSistema(int nroBanda) {

        for (Banda b : this.getBandasSistema()) {
            if (b.soyLaBanda(nroBanda)) {
                return b;
            }
        }
        return null;

    }

}
