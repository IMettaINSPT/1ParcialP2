/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Juicio implements Serializable {

    private IJuez juezActuante;
    private Date fechaJuicio;
    private IDelito delito;
    private Date fechaInicioCondena;
    private Date fechaFinCondena;

    public Juicio(IJuez juez, Date fechaJuicio, IDelito delito, Date fechaICond, Date fechaFCond) {
        this.juezActuante = juez;
        this.fechaJuicio = fechaJuicio;
        this.delito = delito;
        this.fechaInicioCondena = fechaICond;
        this.fechaFinCondena = fechaFCond;
    }

    public Juicio(IJuez juez, Date fechaJuicio, IDelito delito) {
        this.juezActuante = juez;
        this.fechaJuicio = fechaJuicio;
        this.delito = delito;
    }

    public Juicio() {

    }

    public void darSentencia(boolean condenado, Date fechaInicioCondena, Date fechaFinCondena) {
        this.delito.setConCondenado(condenado);
        if (condenado) {
            this.fechaInicioCondena = fechaInicioCondena;
            this.fechaFinCondena = fechaFinCondena;
        }
    }

    public IJuez getJuezActuante() {
        return juezActuante;
    }

    public Date getFechaJuicio() {
        return fechaJuicio;
    }

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

    public String getInfoJuicio() {
        StringBuilder sb = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        sb.append("Juez: ").append(this.juezActuante.getInfoJuez());
        sb.append("Fecha Juicio: ").append(dateFormat.format(this.fechaJuicio));
        sb.append("Delito: ").append(delito.getInfoCompletaDelito());
        sb.append("Fecha inicio condena: ").append(dateFormat.format(this.fechaInicioCondena));
        sb.append("Fecha fin condena: ").append(dateFormat.format(this.fechaFinCondena));
        return sb.toString();
    }
}
