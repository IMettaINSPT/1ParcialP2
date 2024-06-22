package sistemapoliciafederal;

import java.util.Date;

/**
 *
 * @author IMetta
 */
public interface IDelito {
 
    public Date getFechaDelito();
    public void setFechaDelito(Date fechaDelito);
    public PersonaDetenida getDelincuente();
    public void setDelincuente(PersonaDetenida delincuente);
    public String getInfoBasicaDelito();
    public String getInfoCompletaDelito();
    public void setConCondenado(boolean condena);
    public boolean getConCondena();
}
