package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Objects;

public class Vigilante implements Serializable {

    private String codigo;
    private int edad;
    private boolean usaArna;
    private Contrato contrato;

    public Vigilante(String codigo, int edad, boolean usaArma, Contrato contrato) {
        this.codigo = codigo;
        this.edad = edad;
        this.usaArna = usaArma;
        this.contrato = contrato;
    }

    public Vigilante(String codigo, int edad, boolean usaArma) {
        this.codigo = codigo;
        this.edad = edad;
        this.usaArna = usaArma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isUsaArna() {
        return usaArna;
    }

    public void setUsaArna(boolean usaArna) {
        this.usaArna = usaArna;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public boolean soyElVigilante(String cod) {
        return (this.getCodigo().equals(cod));
    }

    public String getInfoVigilante() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo vigilante ").append(this.codigo);
        sb.append("Edad").append(this.edad);
        sb.append("Usa arma: ").append(this.usaArna);
        sb.append("Datos del contrato;").append(this.contrato.getInfoContrato());

        return sb.toString();
    }

}
