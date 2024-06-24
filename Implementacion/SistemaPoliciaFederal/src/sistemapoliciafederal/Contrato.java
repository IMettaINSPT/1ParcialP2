/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contrato implements Serializable {

    private Date fechaContrato;
    private boolean conArma;

    public Contrato(Date fechaContrato, boolean conArma) {
        this.fechaContrato = fechaContrato;
        this.conArma = conArma;
    }

    public boolean soyElContrato(Date fecha, boolean conArma) {
        return ((this.isConArma() == conArma) && (this.getFechaContrato().equals(fecha)));
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public void setConArma(boolean conArma) {
        this.conArma = conArma;
    }

    public boolean isConArma() {
        return conArma;
    }

    public String getInfoContrato() {
        StringBuilder sb = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        sb.append("Fecha contrato: ").append(dateFormat.format(this.fechaContrato)).append("\n");
        sb.append("¿El contrato es con arma?: ").append(this.conArma).append("\n");
        return sb.toString();
    }

}
