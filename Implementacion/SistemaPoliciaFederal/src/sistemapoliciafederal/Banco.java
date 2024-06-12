package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;

public class Banco implements Serializable {

    private String codigoBanco;
    private String domicilioCentral;
    private List<Sucursal> sucursales;
    
    public Banco(String codigo, String domicilio) {
        this.codigoBanco = codigo;
        this.domicilioCentral = domicilio;
    }
    public void AddSucursal(Sucursal suc)
    {
        this.sucursales.add(suc);
    }

    public List<Sucursal> getSucursales()
    {
        return this.sucursales;
    }
    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getDomicilioCentral() {
        return domicilioCentral;
    }

    public void setDomicilioCentral(String domicilioCentral) {
        this.domicilioCentral = domicilioCentral;
    }
}
