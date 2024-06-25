package sistemapoliciafederal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Delito implements IDelito, Serializable {

    private Date fechaDelito;
    private PersonaDetenida delincuente;
    private boolean condenado;

    public Delito(Date fechaDelito, PersonaDetenida delincuente, boolean condena) {
        this.fechaDelito = fechaDelito;
        this.delincuente = delincuente;
        this.condenado = condena;
    }

    public boolean soyElDelito(Date fechaDelito, PersonaDetenida delincuente, boolean condena) {

        return ((this.getConCondena() == condena)
                && this.getDelincuente().soyElDelincuente(delincuente)
                && this.getFechaDelito().equals(fechaDelito));
    }

    @Override
    public Date getFechaDelito() {
        return fechaDelito;
    }

    @Override
    public void setFechaDelito(Date fechaDelito) {
        this.fechaDelito = fechaDelito;
    }

    @Override
    public PersonaDetenida getDelincuente() {
        return delincuente;
    }

    @Override
    public void setDelincuente(PersonaDetenida delincuente) {
        this.delincuente = delincuente;
    }

    @Override
    public void setConCondenado(boolean condena) {
        this.condenado = condena;
    }

    @Override
    public boolean getConCondena() {
        return this.condenado;
    }

    @Override
    public String getInfoBasicaDelito() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha del delito:").append(EntradaSalida.mostrarDate(fechaDelito));
        sb.append("Con Condena:").append(this.condenado);
        return sb.toString();
    }

    @Override
    public String getInfoCompletaDelito() {
        StringBuilder sb = new StringBuilder();
        sb.append("Informacion Delito:").append("\n\n");
        sb.append("Fecha del delito:").append(EntradaSalida.mostrarDate(fechaDelito)).append("\n");
        sb.append("Persona detenida:").append(delincuente.getInfoPersonaDetenida()).append("\n");
        sb.append("Con Condena:").append(this.condenado).append("\n");

        return sb.toString();
    }
}
