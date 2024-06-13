/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class Banda {
    private String nombre;
    private String actividad;
    
    public Banda(String nombre, String actividad) {
        this.nombre = nombre;
        this.actividad = actividad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getActividad() {
        return actividad;
    }
    
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}
