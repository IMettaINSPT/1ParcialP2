/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
    public String getInfoDelito();
    public void setConCondenado(boolean condena);
    public boolean getConCondena();
}
