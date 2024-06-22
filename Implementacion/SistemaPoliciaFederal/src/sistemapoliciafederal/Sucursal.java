package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class Sucursal implements Serializable {

    private String codigoSucursal;
    private String domicilio;
    private int numeroEmpleados;
    private List<Delito> delitos;
    private List<Contrato> contratos;
    private List<Vigilante> vigilantes;

    public Sucursal(String codigo, String domicilio, int empleados) {
        this.codigoSucursal = codigo;
        this.domicilio = domicilio;
        this.numeroEmpleados = empleados;
        this.delitos = new ArrayList<>();
        this.contratos = new ArrayList<>();
        this.vigilantes = new ArrayList<>();
    }

    public String getInfoSucursal() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo sucursal : ").append(this.codigoSucursal);
        sb.append("\n Domicilio : ").append(this.domicilio);
        sb.append("\n Cantidad de empleados : ").append(this.numeroEmpleados);
        sb.append("Delitos :");
        for (Delito del : this.delitos) {
            sb.append("\n delito: ").append(del.getInfoCompletaDelito());
        }
        for (Contrato con : this.contratos) {
            sb.append("\n contrato: ").append(con.getInfoContrato());
        }
        return sb.toString();
    }

    public void NotificarDelito(Date fecha, PersonaDetenida delincuente) {
        Delito delito = new Delito(fecha, delincuente, false);
        delitos.add(delito);
    }

    public void GenerarContrato(boolean contratoConArma, Date fechaContrato, Vigilante vigilante) {
        Contrato contrato = new Contrato(fechaContrato, contratoConArma);
        vigilante.setContrato(contrato);
        this.contratos.add(contrato);
    }

    // Métodos de la segunda clase Sucursal
    public void agregarVigilante(Vigilante vigilante) {
        this.vigilantes.add(vigilante);
    }

    public void mostrarInformacion() {
        System.out.println("  Sucursal: " + codigoSucursal + ", Domicilio: " + domicilio + ", Empleados: " + numeroEmpleados);
        for (Vigilante vigilante : vigilantes) {
            vigilante.mostrarInformacion();
        }
    }

    // Getters y Setters combinados
    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    public List<Delito> getDelitos() {
        return delitos;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public List<Vigilante> getVigilantes() {
        return vigilantes;
    }

    Object getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
