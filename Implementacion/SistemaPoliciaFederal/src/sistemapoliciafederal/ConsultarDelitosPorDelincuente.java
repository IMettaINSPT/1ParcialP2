
package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author IMetta
 */
public class ConsultarDelitosPorDelincuente implements IConsultaDelitos ,Serializable{

    private final PersonaDetenida delincuente;
    private final List<IDelito> delitos;

    public ConsultarDelitosPorDelincuente(List<IDelito> delitos, PersonaDetenida delincuente) {
        this.delincuente = delincuente;
        this.delitos = delitos;
    }

    @Override
    public List<IDelito> getDelitos() {

        return this.delitos.stream().filter(d -> d.getDelincuente().soyDelincuente(delincuente))
                .collect(Collectors.toList());

    }

}
