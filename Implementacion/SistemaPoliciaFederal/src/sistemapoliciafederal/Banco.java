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

    public void AddSucursal(Sucursal suc) {
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
        sb.append("Codigo Banco : ").append(this.codigoBanco);
        sb.append("Domicilio central : ").append(this.domicilioCentral);
        for (Sucursal s : this.sucursales) {
            sb.append("\nSucursal :").append(s.getInfoSucursal());
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
