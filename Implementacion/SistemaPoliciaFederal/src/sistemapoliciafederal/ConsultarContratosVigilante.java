package sistemapoliciafederal;

import java.util.*;

public class ConsultarContratosVigilante implements IConsultaContrato {

    private final Vigilante vigilante;

    public ConsultarContratosVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

    @Override
    public List<Contrato> getContratos() {
        List<Contrato> v = new ArrayList<>();
        v.add(this.vigilante.getContrato());
        return v;
    }

}
