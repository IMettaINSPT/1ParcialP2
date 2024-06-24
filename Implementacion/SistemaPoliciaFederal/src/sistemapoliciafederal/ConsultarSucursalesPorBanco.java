package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;

public class ConsultarSucursalesPorBanco implements IConsultaSucursal,Serializable {

    private final Banco banco;

    public ConsultarSucursalesPorBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public List<Sucursal> getSucursales() {
        return this.banco.getSucursales();
    }
}
