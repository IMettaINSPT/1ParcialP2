/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Date;

public class Juicio implements Serializable {

 private IJuez juezActuante ;
 private Date fechaJuicio;
 private IDelito delito;
 private Date fechaInicioCondena;
 private Date fechaFinCondena;
 
 public Juicio(IJuez juez, Date fechaJuicio, IDelito delito, Date fechaICond, Date fechaFCond)
 {
     this.juezActuante = juez;
     this.fechaJuicio = fechaJuicio;
     this.delito = delito;
     this.fechaInicioCondena = fechaICond;
     this.fechaFinCondena = fechaFCond; 
 }
 
 public Juicio(){
 
 }
    public IJuez getJuezActuante() {
        return juezActuante;
    }

    /**
     * @return the fechaJuicio
     */
    public Date getFechaJuicio() {
        return fechaJuicio;
    }

    /**
     * @return the delito
     */
    public IDelito getDelito() {
        return delito;
    }

    /**
     * @return the fechaInicioCondena
     */
    public Date getFechaInicioCondena() {
        return fechaInicioCondena;
    }

    /**
     * @return the fechaFinCondena
     */
    public Date getFechaFinCondena() {
        return fechaFinCondena;
    }      
}
