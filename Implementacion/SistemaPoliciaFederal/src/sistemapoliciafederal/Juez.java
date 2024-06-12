package sistemapoliciafederal;

import java.io.Serializable;

public class Juez implements IJuez, Serializable{
    private String claveInternaJuzgado;
    private String nombreCompleto;
    private int añosServicio;    
    
    
    @Override
    public void setClaveJuzgado(String clave) {
           this.claveInternaJuzgado = clave;
    }

    @Override
    public void setNombreCompleto(String nom) {
        this.nombreCompleto =nom;
    }

    @Override
    public void setAñosServicio(int años) {
        this.añosServicio = años;
    }

    @Override
    public String getDatosJuez() {

     StringBuilder datos = new StringBuilder();
     datos.append("Clave interna juzgado:").append(this.claveInternaJuzgado);
     datos.append("Nombre completo:").append(this.nombreCompleto);
     datos.append("Años servicio:").append(this.añosServicio);
   
     return datos.toString();
    }


}
