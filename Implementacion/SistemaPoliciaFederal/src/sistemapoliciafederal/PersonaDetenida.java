/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class PersonaDetenida {
    private String nombre;
    private String delito;
    
    public PersonaDetenida(String nombre, String delito) {
        this.nombre = nombre;
        this.delito = delito;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDelito() {
        return delito;
    }
    
    public void setDelito(String delito) {
        this.delito = delito;
    }
}
