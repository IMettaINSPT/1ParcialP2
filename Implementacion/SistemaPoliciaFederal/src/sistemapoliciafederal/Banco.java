package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class Banco implements Serializable {

    private String codigoBanco;
    private String domicilioCentral;
    private List<Sucursal> sucursales;

    public Banco(String codigo, String domicilio) {
        this.codigoBanco = codigo;
        this.domicilioCentral = domicilio;
        sucursales = new ArrayList<>();
    }

    public boolean soyElBanco(String codigo) {
        return (this.getCodigoBanco().equals(codigo));
    }

    public void addSucursal(String codigo, String domicilio, int nroEmpleados) {
        //composicion, la sucursal se crea a traves del banco
        Sucursal suc = new Sucursal(codigo, domicilio, nroEmpleados);
        this.sucursales.add(suc);
    }

    public List<Sucursal> getSucursales() {
        return this.sucursales;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getInfoBanco() {
        StringBuilder sb = new StringBuilder();
        sb.append("Informacion Banco\n");
        sb.append("Codigo Banco : ").append(this.codigoBanco).append("\n");
        sb.append("Domicilio central : ").append(this.domicilioCentral).append("\n");
        for (Sucursal s : this.sucursales) {
            sb.append("\nSucursal banco:").append("\n").append(s.getInfoSucursal());
        }
        return sb.toString();
    }

    public String getDomicilioCentral() {
        return domicilioCentral;
    }

    public void setDomicilioCentral(String domicilioCentral) {
        this.domicilioCentral = domicilioCentral;
    }
}
