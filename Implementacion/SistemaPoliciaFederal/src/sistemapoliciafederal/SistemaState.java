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

    public SistemaState() {
        juicios = new ArrayList<>();
        jueces = new ArrayList<>();
        vigilantes = new ArrayList<>();
        bancos = new ArrayList<>();
        delitos = new ArrayList<>();
        bandas = new ArrayList<>();
        usuarios = new ArrayList<>();
        delincuentes = new ArrayList<>();
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
            for(Sucursal s: bco.getSucursales())
            {
            vigilantesOcupados.addAll((Set)s.getContratos());
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

}
