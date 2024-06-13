/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class Juicio {
    private String caso;
    private String juez;
    
    public Juicio(String caso, String juez) {
        this.caso = caso;
        this.juez = juez;
    }
    
    public String getCaso() {
        return caso;
    }
    
    public void setCaso(String caso) {
        this.caso = caso;
    }
    
    public String getJuez() {
        return juez;
    }
    
    public void setJuez(String juez) {
        this.juez = juez;
    }
}
