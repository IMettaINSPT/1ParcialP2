package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Sucursal implements Serializable  {

    private String codigo;
    private String domicilio;
    private int nroEmpleados;
    private List<Delito> delitos;
    private List<Contrato> contratos;

    public Sucursal(String codigo, String domicilio, int nroEmpleados) {
        this.codigo = codigo;
        this.domicilio = domicilio;
        this.nroEmpleados = nroEmpleados;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getNroEmpleados() {
        return nroEmpleados;
    }

    public void setNroEmpleados(int nroEmpleados) {
        this.nroEmpleados = nroEmpleados;
    }

    public List<Delito> getDelitos() {
        return this.delitos;
    }

    public List<Contrato> getContratos() {
        return this.contratos;
    }

    /*Por defecto al notificar un delito no hay condena porque aun no se hizo un juicio**/
    public void NotificarDelito(Date fecha, PersonaDetenida delincuente) {
        Delito delito = new Delito(fecha, delincuente, false);
        delitos.add(delito);
    }

    public void GenerarContrato(boolean contratoConArma, Date fechaContrato, Vigilante vigilante) {
        Contrato contrato = new Contrato(fechaContrato, contratoConArma);
        vigilante.setContrato(contrato);
        this.contratos.add(contrato);
    }

}
