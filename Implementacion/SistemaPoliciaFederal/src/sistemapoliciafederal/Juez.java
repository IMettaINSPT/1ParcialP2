package sistemapoliciafederal;

import java.io.Serializable;

public class Juez implements IJuez, Serializable {

    private String claveInternaJuzgado;
    private String nombreCompleto;
    private int añosServicio;

    public Juez() {
    }

    public Juez(String clave, String nombre, int añosServicio) {
        this.claveInternaJuzgado = clave;
        this.nombreCompleto = nombre;
        this.añosServicio = añosServicio;

    }

    public boolean soyElJuez(String claveJuzgado) {
        return (this.getClaveJuzgado().equals(claveJuzgado));
    }

    public String getClaveJuzgado() {
        return this.claveInternaJuzgado;
    }

    @Override
    public void setClaveJuzgado(String clave) {
        this.claveInternaJuzgado = clave;
    }

    @Override
    public void setNombreCompleto(String nom) {
        this.nombreCompleto = nom;
    }

    @Override
    public void setAñosServicio(int años) {
        this.añosServicio = años;
    }

    @Override
    public String getInfoJuez() {

        StringBuilder datos = new StringBuilder();
        datos.append("Informacion Juez:").append("\n\n");
        datos.append("Clave interna juzgado:").append(this.claveInternaJuzgado).append("\n");
        datos.append("Nombre completo:").append(this.nombreCompleto).append("\n");
        datos.append("Años servicio:").append(this.añosServicio).append("\n");

        return datos.toString();
    }

}
