package sistemapoliciafederal;

import java.io.Serializable;
import java.util.*;

public class ConsultarContratosBanco implements IConsultaContrato,Serializable {

    private final Banco banco;

    public ConsultarContratosBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public List<Contrato> getContratos() {
        List<Contrato> contratos = new ArrayList<>();
        for (Sucursal suc : banco.getSucursales()) {
            contratos.addAll(suc.getContratos());
        }
        return contratos;
    }

}
