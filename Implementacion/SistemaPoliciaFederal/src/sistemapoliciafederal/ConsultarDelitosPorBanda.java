package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ConsultarDelitosPorBanda implements IConsultaDelitos ,Serializable{

    private final Banda banda;
    private final List<IDelito> delitos;

    // la referencia a la banda y la lista de delitos me viene inyectada del llamador (en este sist sistemaState las contiene)
    public ConsultarDelitosPorBanda(List<IDelito> delitos, Banda banda) {
        this.banda = banda;
        this.delitos = delitos;
    }

    @Override
    public List<IDelito> getDelitos() {
        List<IDelito> delitosFinal = new ArrayList<>();
        for (PersonaDetenida delincuente : this.banda.getMiembros()) {
            delitosFinal.addAll(
                    this.delitos.stream()
                            .filter(delito -> delito.getDelincuente().getCodigo().equals(delincuente.getCodigo()))
                            .collect(Collectors.toList())
            );
        }
        return delitosFinal;
    }

}
