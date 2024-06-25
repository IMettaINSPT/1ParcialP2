package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class Banda implements Serializable {

    private int nroBanda;
    private List<PersonaDetenida> delincuentes;

    public Banda(int nroBanda) {
        this.nroBanda = nroBanda;
        delincuentes = new ArrayList<>();
    }

    public int getNroBanda() {
        return nroBanda;
    }

    public void setNroBanda(int nroBanda) {
        this.nroBanda = nroBanda;
    }

    public List<PersonaDetenida> getMiembros() {
        return delincuentes;
    }

    public void addMiembro(PersonaDetenida delincuente) {

        if (this.delincuentes.stream().anyMatch(p -> (p.getCodigo() == null ? delincuente.getCodigo() == null : p.getCodigo().equals(delincuente.getCodigo())))) {
            EntradaSalida.mostrarError("El delincuente ya existe");

        } else {
            this.delincuentes.add(delincuente);
        }
    }

    public boolean soyLaBanda(int nroBanda) {
        return this.getNroBanda() == nroBanda;
    }

    public String getInfoBanda() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nro.Banda: ").append(this.nroBanda).append("\n");
        for (PersonaDetenida delincuente : this.delincuentes) {
            sb.append("Delincuente miembro: ").append("\n").append(delincuente.getInfoPersonaDetenida());
        }
        return sb.toString();
    }

}
