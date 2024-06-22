package sistemapoliciafederal;

import java.util.*;

public class ConsultarContratosSucursal implements IConsultaContrato {

    private final Sucursal sucursal;

    public ConsultarContratosSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public List<Contrato> getContratos() {
        return this.sucursal.getContratos();
    }
}

