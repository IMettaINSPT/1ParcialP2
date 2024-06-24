package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class ConsultarDelincuentesPorSucursal implements IConsultaDelincuente ,Serializable{

    private final Sucursal sucursal;

    public ConsultarDelincuentesPorSucursal(Sucursal s) {
        this.sucursal = s;
    }

    @Override
    public List<PersonaDetenida> getDelincuentes() {
        List<PersonaDetenida> delincuentes = new ArrayList<>();
        for (Delito d : this.sucursal.getDelitos()) {
            delincuentes.add(d.getDelincuente());

        }
        return delincuentes;
    }

}
