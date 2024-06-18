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
        this.delincuentes.add(delincuente);
    }

    public String getInfoBanda() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nro Banda ").append(this.nroBanda);
        for (PersonaDetenida delincuente : this.delincuentes) {
            sb.append("Delincuente miembro :").append(delincuente.getInfoPersonaDetenida());
        }
        return sb.toString();
    }
}
