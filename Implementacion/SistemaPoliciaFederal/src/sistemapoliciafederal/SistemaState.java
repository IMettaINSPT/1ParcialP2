package sistemapoliciafederal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaState implements Serializable {
    private List<Juicio> juicios;
    private List<Juez> jueces;
    private List<Vigilante> vigilantes;
    private List<Banco> bancos;
    private List<IDelito> delitos;
    private List<Banda> bandas;
    private List<Usuario> usuarios;
    private List<PersonaDetenida> detenidos;

    public SistemaState() {
        this.juicios = new ArrayList<>();
        this.jueces = new ArrayList<>();
        this.vigilantes = new ArrayList<>();
        this.bancos = new ArrayList<>();
        this.delitos = new ArrayList<>();
        this.bandas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.detenidos = new ArrayList<>();
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

    public void setJuicios(List<Juicio> juicios) {
        this.juicios = juicios;
    }

    public List<Juez> getJueces() {
        return jueces;
    }

    public void setJueces(List<Juez> jueces) {
        this.jueces = jueces;
    }

    public List<Vigilante> getVigilantes() {
        return vigilantes;
    }

    public void setVigilantes(List<Vigilante> vigilantes) {
        this.vigilantes = vigilantes;
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }

    public List<IDelito> getDelitos() {
        return delitos;
    }

    public void setDelitos(List<IDelito> delitos) {
        this.delitos = delitos;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<PersonaDetenida> getDetenidos() {
        return detenidos;
    }

    public void setDetenidos(List<PersonaDetenida> detenidos) {
        this.detenidos = detenidos;
    }

    public void addJuicio(Juicio j) {
        this.juicios.add(j);
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

    public void addDelito(IDelito delito) {
        this.delitos.add(delito);
    }

    public void addBanda(Banda j) {
        this.bandas.add(j);
    }

    public void addUsuario(Usuario u) {
        this.usuarios.add(u);
    }

    public void addUsuario(List<Usuario> users) {
        List<Usuario> usuariosNuevos = users.stream()
                                             .filter(u -> !this.usuarios.contains(u))
                                             .collect(Collectors.toList());

        this.usuarios.addAll(usuariosNuevos);
    }

    public void addDetenido(PersonaDetenida detenido) {
        this.detenidos.add(detenido);
    }
}
