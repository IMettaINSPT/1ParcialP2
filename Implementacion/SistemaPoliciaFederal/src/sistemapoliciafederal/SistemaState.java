package sistemapoliciafederal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SistemaState implements Serializable { 
    private List<Juicio> juicios;
    private List<Juez> jueces;
    private List<Vigilante> vigilantes;
    private List<Banco> bancos;
    private List<IDelito> delitos;
    private List<Banda> bandas;
    private List<Usuario> usuarios;

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

    public void addJuez(Juez j) {
        this.jueces.add(j);
    }

    public void addVigilante(Vigilante j) {
        this.vigilantes.add(j);
    }

    public void addBanco(Banco j) {
        this.bancos.add(j);
    }

    public List<IDelito> getDelitos() {
        return delitos;
    }


    public void addDelito(Delito delito) {
        this.delitos.add(delito);
    }


    public void addBanda(Banda j) {
        this.bandas.add(j);
    }

    /**
     * @param juicios the juicios to set
     */
    public void setJuicios(List<Juicio> juicios) {
        this.juicios = juicios;
    }

    /**
     * @param jueces the jueces to set
     */
    public void setJueces(List<Juez> jueces) {
        this.jueces = jueces;
    }

    /**
     * @param vigilantes the vigilantes to set
     */
    public void setVigilantes(List<Vigilante> vigilantes) {
        this.vigilantes = vigilantes;
    }

    /**
     * @param bancos the bancos to set
     */
    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }

    /**
     * @param delitos the delitos to set
     */
    public void setDelitos(List<IDelito> delitos) {
        this.delitos = delitos;
    }

    /**
     * @param bandas the bandas to set
     */
    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
