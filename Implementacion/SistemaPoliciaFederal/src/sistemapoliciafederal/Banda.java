package sistemapoliciafederal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banda implements Serializable {

    private int numeroBanda;
    private List<PersonaDetenida> delincuentes;
    private int numeroMiembros;

    public Banda(int numeroBanda) {
        this.numeroBanda = numeroBanda;
        this.delincuentes = new ArrayList<>();
        this.numeroMiembros = 0;
    }

    public int getNumeroBanda() {
        return numeroBanda;
    }

    public List<PersonaDetenida> getDelincuentes() {
        return delincuentes;
    }

    public void addDelincuente(PersonaDetenida delincuente) {
        this.delincuentes.add(delincuente);
        this.numeroMiembros++;
    }

    public String getInfoBanda() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nro Banda ").append(this.numeroBanda);
        for (PersonaDetenida delincuente : this.delincuentes) {
            sb.append("\nDelincuente miembro: ").append(delincuente.getInfoPersonaDetenida());
        }
        return sb.toString();
    }

    public int getNumeroMiembros() {
        return numeroMiembros;
    }

    public void mostrarInformacion() {
        System.out.println("Banda: " + numeroBanda + ", Miembros: " + numeroMiembros);
    }

    Iterable<PersonaDetenida> getMiembros() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
