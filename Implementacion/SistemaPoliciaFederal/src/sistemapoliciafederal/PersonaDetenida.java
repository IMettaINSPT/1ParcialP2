package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Objects;

public class PersonaDetenida implements Serializable {

    private String codigo;
    private String nombreCompleto;

    public PersonaDetenida() {
    }

    public PersonaDetenida(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombreCompleto = nombre;
    }

    public boolean soyElDelincuente(String codigo) {
        return this.getCodigo().equals(codigo);
    }

    public boolean soyElDelincuente(PersonaDetenida p) {
        return this.soyElDelincuente(p.getCodigo());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getInfoPersonaDetenida() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre compleo: ").append(this.nombreCompleto);
        sb.append("Codigo: ").append(this.codigo);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof PersonaDetenida) {
            PersonaDetenida p = (PersonaDetenida) obj;
            return p.getCodigo().equals(this.getCodigo());
        } else {
            return false;
        }
    }

    //Necesario para el tener un codigo unico de cada clase
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        hash = 79 * hash + Objects.hashCode(this.nombreCompleto);
        return hash;
    }

}
