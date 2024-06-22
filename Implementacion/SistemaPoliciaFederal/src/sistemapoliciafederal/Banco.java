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
        this.sucursales = new ArrayList<>();
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
        sb.append("\nDomicilio central : ").append(this.domicilioCentral);
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

    public void agregarSucursal(Sucursal sucursal) {
        this.sucursales.add(sucursal);
    }

    public void mostrarInformacion() {
        System.out.println("Banco: " + codigoBanco + ", Domicilio Central: " + domicilioCentral);
        for (Sucursal sucursal : sucursales) {
            sucursal.mostrarInformacion();
        }
    }
}
