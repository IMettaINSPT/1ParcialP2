package sistemapoliciafederal;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultarDelincuentesPorCodigo implements IConsultaDelincuente {

    private final String codigo;
    private final List<PersonaDetenida> delincuentes;

    public ConsultarDelincuentesPorCodigo(List<PersonaDetenida> delincuentes, String codigo) {
        this.codigo = codigo;
        this.delincuentes = delincuentes;
    }

    @Override
    public List<PersonaDetenida> getDelincuentes() {
        return this.delincuentes.stream()
                .filter(d -> d.getCodigo().equals(this.codigo))
                .collect(Collectors.toList());
    }
}
