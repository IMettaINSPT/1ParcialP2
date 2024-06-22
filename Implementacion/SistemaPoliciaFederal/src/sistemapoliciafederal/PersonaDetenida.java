package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonaDetenida implements Serializable {

    private String codigo;
    private String nombreCompleto;
    private Banda banda;
    private Map<Sucursal, Date> asaltos;

    public PersonaDetenida(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombreCompleto = nombre;
        this.banda = null; // Inicializamos la banda como null por defecto
        this.asaltos = new HashMap<>();
    }

    public PersonaDetenida(String codigo, String nombre, Banda banda) {
        this.codigo = codigo;
        this.nombreCompleto = nombre;
        this.banda = banda;
        this.asaltos = new HashMap<>();
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

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public void agregarAsalto(Sucursal sucursal, Date fecha) {
        this.asaltos.put(sucursal, fecha);
    }

    public String getInfoPersonaDetenida() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre completo: ").append(this.nombreCompleto).append(", ");
        sb.append("Código: ").append(this.codigo);
        if (banda != null) {
            sb.append(", Banda: ").append(banda.getNumeroBanda());
        }
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.codigo);
        hash = 79 * hash + Objects.hashCode(this.nombreCompleto);
        return hash;
    }

    public void mostrarInformacion() {
        System.out.println("Persona Detenida: " + codigo + ", Nombre: " + nombreCompleto + ", Banda: " + (banda != null ? banda.getNumeroBanda() : "N/A"));
        for (Map.Entry<Sucursal, Date> entry : asaltos.entrySet()) {
            System.out.println("  Asalto a Sucursal: " + entry.getKey().getCodigoSucursal() + " en fecha: " + entry.getValue());
        }
    }
}
