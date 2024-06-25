package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class ConsultarContratosVigilante implements IConsultaContrato, Serializable {

    private final Vigilante vigilante;

    public ConsultarContratosVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

    @Override
    public List<Contrato> getContratos() {
        List<Contrato> v = new ArrayList<>();
        if (Objects.isNull(this.vigilante.getContrato())) {
            EntradaSalida.mostrarString("No hay contratos para mostrar\n");
            return v;
        }
        v.add(this.vigilante.getContrato());
        return v;
    }

}
