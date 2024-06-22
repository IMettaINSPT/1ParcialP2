package sistemapoliciafederal;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author IMetta
 */
public class ConsultarDelitosPorDelincuente implements IConsultaDelitos {

    private final PersonaDetenida delincuente;
    private final List<Delito> delitos;
    
    public ConsultarDelitosPorDelincuente(List<Delito> delitos, PersonaDetenida delincuente)
    {
        this.delincuente = delincuente;
        this.delitos = delitos;    
    }
    
    @Override
    public List<IDelito> getDelitos() {

        return this.delitos.stream().filter(d-> d.getDelincuente().equals(delincuente))
                .collect(Collectors.toList());

    }

}
