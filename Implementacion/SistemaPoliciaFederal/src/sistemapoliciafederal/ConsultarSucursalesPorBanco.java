package sistemapoliciafederal;

import java.util.List;

public class ConsultarSucursalesPorBanco implements IConsultaSucursal {
    private final List<Sucursal> sucursales;
    private final Banco banco;

    public ConsultarSucursalesPorBanco(List<Sucursal> sucursales, Banco banco) {
        this.banco = banco;
        this.sucursales = sucursales;
    }
    @Override
    public List<Sucursal> getSucursales() {
        return this.banco.getSucursales();
    }
}
