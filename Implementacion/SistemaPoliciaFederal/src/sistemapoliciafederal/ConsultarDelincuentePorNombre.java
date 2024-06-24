package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarDelincuentePorNombre implements IConsultaDelincuente ,Serializable{

    private final String nombre;
    private final List<PersonaDetenida> delincuentes;

    public ConsultarDelincuentePorNombre(List<PersonaDetenida> delincuentes, String nombre) {
        this.nombre = nombre;
        this.delincuentes = delincuentes;
    }

    @Override
    public List<PersonaDetenida> getDelincuentes() {
        return this.delincuentes.stream()
                .filter(d -> d.getNombreCompleto().equals(this.nombre))
                .collect(Collectors.toList());
    }
}
