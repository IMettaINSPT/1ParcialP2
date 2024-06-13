/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class Delito {
    private String descripcion;
    private String condena;
    
    public Delito(String descripcion, String condena) {
        this.descripcion = descripcion;
        this.condena = condena;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getCondena() {
        return condena;
    }
    
    public void setCondena(String condena) {
        this.condena = condena;
    }
}

