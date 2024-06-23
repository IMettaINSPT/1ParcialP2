package sistemapoliciafederal;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultarDelitosConCondena implements IConsultaDelitos {

    private final List<IDelito> delitos;

    public ConsultarDelitosConCondena(List<IDelito> delitos) {
        this.delitos = delitos;
    }

    @Override
    public List<IDelito> getDelitos() {
        return delitos.stream().filter(d -> d.getConCondena()).collect(Collectors.toList());
    }

}
