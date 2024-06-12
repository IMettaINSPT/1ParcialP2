package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;

public class Banda implements Serializable  {

    private int nroBanda;
    private List<PersonaDetenida> delincuentes;

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
}
