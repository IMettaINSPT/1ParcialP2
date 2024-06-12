
package sistemapoliciafederal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Delito implements IDelito , Serializable{

    private Date fechaDelito;
    private PersonaDetenida delincuente;
    private boolean condenado;

    public Delito(Date fechaDelito, PersonaDetenida delincuente, boolean condena) {
        this.fechaDelito = fechaDelito;
        this.delincuente = delincuente;
        this.condenado = condena;
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
    public String getInfoDelito() {
        StringBuilder sb = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        sb.append("Fecha del delito:").append(dateFormat.format(fechaDelito));
        sb.append("Persona detenida:").append(delincuente.toString());
        return sb.toString();
    }

}
