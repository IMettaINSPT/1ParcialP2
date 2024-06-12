package sistemapoliciafederal;

import java.io.Serializable;

public class PersonaDetenida implements Serializable {

    private String codigo;
    private String nombreCompleto;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre compleo: ").append(this.nombreCompleto);
        sb.append("Codigo: ").append(this.codigo);
        return sb.toString();
    }
}
