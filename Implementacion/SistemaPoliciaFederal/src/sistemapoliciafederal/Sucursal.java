package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class Sucursal implements Serializable {

    private String codigo;
    private String domicilio;
    private int nroEmpleados;
    private List<Delito> delitos;
    private List<Contrato> contratos;

    public Sucursal(String codigo, String domicilio, int nroEmpleados) {
        this.codigo = codigo;
        this.domicilio = domicilio;
        this.nroEmpleados = nroEmpleados;
        delitos = new ArrayList<>();
        contratos = new ArrayList<>();
    }

    public boolean soyLaSucursal(String codigo) {
        return this.getCodigo().equals(codigo);
    }

    public void cargarDelito(Delito d) {
        this.delitos.add(d);
    }

    public String getInfoSucursal() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCodigo sucursal : ").append(this.codigo);
        sb.append("\n Domicilio : ").append(this.domicilio);
        sb.append("\n Cantidad de empleados : ").append(this.nroEmpleados);
        sb.append("\nDelitos :");
        for (Delito del : this.delitos) {
            sb.append("\n delito: ").append(del.getInfoCompletaDelito());
        }
        for (Contrato con : this.contratos) {
            sb.append("\n contrato: ").append(con.getInfoContrato());
        }
        return sb.toString();
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
