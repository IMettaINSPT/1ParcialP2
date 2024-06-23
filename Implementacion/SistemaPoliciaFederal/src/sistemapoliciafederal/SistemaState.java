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

    public List<Juicio> getJuicios() {
        return juicios;
    }

    public List<Juez> getJueces() {
        return jueces;
    }

    public List<Vigilante> getVigilantes() {
        return vigilantes;
    }

    public List<Vigilante> getVigilantesSinContrato() {
        Set<Vigilante> vigilantesOcupados = new HashSet<>();
        for (Banco bco : this.getBancos()) {
            for (Sucursal s : bco.getSucursales()) {
                vigilantesOcupados.addAll((Set) s.getContratos());
            }
        }

        List<Vigilante> vigilantesLibres = this.getVigilantes();
        vigilantesLibres.removeAll(vigilantesOcupados);
        return vigilantesLibres;

    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void addJuicio(Juicio j) {
        this.juicios.add(j);
    }

    public void addUsuario(Usuario u) {
        this.usuarios.add(u);
    }

    public void addUsuario(List<Usuario> users) {
        //Filtro los usuario de la lista que me llega que no existan ya.
        List<Usuario> usuariosNuevos = this.usuarios.stream().filter(u -> users.contains(u)).collect(Collectors.toList());

        for (Usuario u : usuariosNuevos) {

            this.usuarios.add(u);
        }

    }

    public void addJuez(Juez j) {
        this.jueces.add(j);
    }

    public void addVigilante(Vigilante j) {
        this.vigilantes.add(j);
    }

    public void addBanco(Banco j) {
        this.bancos.add(j);
    }

    public Banco getBanco(Sucursal suc) {
        for (Banco b : this.getBancos()) {
            for (Sucursal s : b.getSucursales()) {
                if (s.equals(suc)) {
                    return b;
                }
            }
        }
        EntradaSalida.mostrarError("Banco no encontraod");
        return null;
    }

    public void actualizarBanco(Sucursal s) {
        Banco b = this.getBanco(s);
        this.actualizarBanco(b);
    }

    public void actualizarBanco(Banco b) {
        this.bancos.remove(b);
        this.bancos.add(b);
    }

    public List<IDelito> getDelitos() {
        return delitos;
    }

    public void addDelito(Delito delito) {
        this.delitos.add(delito);
    }

    public void actualizarBanda(Banda b) {
        this.bandas.remove(b);
        this.bandas.add(b);

    }

    public void addBanda(Banda j) {
        this.bandas.add(j);
    }

    public void setJuicios(List<Juicio> juicios) {
        this.juicios = juicios;
    }

    public void setJueces(List<Juez> jueces) {
        this.jueces = jueces;
    }

    public void setVigilantes(List<Vigilante> vigilantes) {
        this.vigilantes = vigilantes;
    }

    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }

    public void setDelitos(List<IDelito> delitos) {
        this.delitos = delitos;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<PersonaDetenida> getDelincuentes() {
        return delincuentes;
    }

    public void setDelincuentes(List<PersonaDetenida> delincuentes) {
        this.delincuentes = delincuentes;
    }

    public void addDelincuente(PersonaDetenida delincuente) {
        this.delincuentes.add(delincuente);
    }

    public Vigilante obtenerVigilanteLibre() {
        Vigilante v = null;
        String codigo = EntradaSalida.leerString("Ingrese el codigo del vigilante a buscar");

        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo del vigilante no puede ser vacio");
            return v;
        }
        for (Vigilante vig : this.getVigilantesSinContrato()) {
            if (vig.soyElVigilante(codigo)) {
                return vig;
            }
        }
        EntradaSalida.mostrarError("No se encuentra el vigilante para el codigo ingresado");
        return v;
    }

    public Vigilante obtenerVigilante() {
        Vigilante v = null;
        String codigo = EntradaSalida.leerString("Ingrese el codigo del vigilante a buscar");

        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo del vigilante no puede ser vacio");
            return v;
        }
        for (Vigilante vig : this.getVigilantes()) {
            if (vig.soyElVigilante(codigo)) {
                return vig;
            }
        }
        EntradaSalida.mostrarError("No se encuentra el vigilante para el codigo ingresado");
        return v;
    }

    public IDelito obtenerDelito() {
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

        for (IDelito dd : this.getDelitos()) {
            if (((Delito) dd).soyElDelito(fechaDelito, personaD, condena)) {
                return ((Delito) dd);
            }
        }

        return d;
    }

    public Contrato obtenerContrato() {
        Contrato contrato = null;
        boolean datosValidos;
        do {
            datosValidos = true;
            Date fechaContrato = EntradaSalida.leerDate("Ingrese la fecha del contrato");
            if (Objects.isNull(fechaContrato)) {
                EntradaSalida.mostrarError("Fecha incorrecta");
                datosValidos = false;
            }
            Sucursal suc = this.obtenerSucursal();
            if (Objects.isNull(suc)) {
                EntradaSalida.mostrarError("La sucursal del banco no existe");
                datosValidos = false;
            }
            boolean esConArma = EntradaSalida.leerBoolean("¿Contrato con arma?");

            if (datosValidos) {
                for (Contrato c : suc.getContratos()) {
                    if (c.soyElContrato(fechaContrato, esConArma)) {
                        return c;
                    }
                }
                EntradaSalida.mostrarError("No se encuentra un contrato para los datos seleccionados. Reintente!");
                datosValidos = false;

            } else {
                EntradaSalida.mostrarError("Los datos ingresados son invalidos. Reingrese los mismos");

            }
        } while (!datosValidos);
        return contrato;

    }

    public IJuez obtenerJuez() {
        IJuez juez = null;
        String codigoInternaJuzgado = EntradaSalida.leerString("Ingrese la clave interna del juzgado");
        for (IJuez j : this.getJueces()) {
            if (((Juez) j).soyElJuez(codigoInternaJuzgado)) {
                return j;
            }
        }
        EntradaSalida.mostrarError("El codigo ingresado no pertenece a un juez");
        return juez;
    }

    public PersonaDetenida obtenerPersonaDetenida() {
        PersonaDetenida p = null;
        String codigoPersona = EntradaSalida.leerString("Ingrese el codigo del delincuente");
        for (PersonaDetenida pd : this.getDelincuentes()) {
            if (pd.soyElDelincuente(codigoPersona)) {
                return pd;
            }
        }
        EntradaSalida.mostrarError("El codigo ingresado no pertenece a un delincuente");
        return p;
    }

    public List<Sucursal> obtenerSucursales() {
        Banco banco = this.obtenerBanco();
        if (Objects.isNull(banco)) {
            EntradaSalida.mostrarError("El banco no existe");
            return null;
        }
        return banco.getSucursales();

    }

    public Sucursal obtenerSucursal() {
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

    public Banco obtenerBanco() {
        Banco bco = null;
        String codigo = EntradaSalida.leerString("Ingrese el codigo del banco a buscar");
        if (codigo.isEmpty()) {
            EntradaSalida.mostrarError("El codigo del banco no puede ser vacio");
            return bco;
        }
        for (Banco banco : this.getBancos()) {
            if (banco.soyElBanco(codigo)) {
                return banco;
            }
        }
        EntradaSalida.mostrarError("No se encuentra el banco para el codigo ingresado");
        return bco;
    }

    public Banda obtenerBanda() {
        int nroBanda = EntradaSalida.leerEntero("Ingrese el nro de la banda");
        if (nroBanda <= 0) {
            EntradaSalida.mostrarError("El nro de banda no puede ser 0");
            return null;
        }
        for (Banda b : this.getBandas()) {
            if (b.soyLaBanda(nroBanda)) {
                return b;
            }
        }
        EntradaSalida.mostrarError("No se encontro ninguna banda");
        return null;

    }

}
