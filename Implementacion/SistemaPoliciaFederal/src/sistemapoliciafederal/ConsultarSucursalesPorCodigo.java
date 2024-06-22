package sistemapoliciafederal;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultarSucursalesPorCodigo implements IConsultaSucursal {

    private final List<Sucursal> sucursales;
    private final String codigo;

    public ConsultarSucursalesPorCodigo(List<Sucursal> sucursales, String codigo) {
        this.codigo = codigo;
        this.sucursales = sucursales;
    }

    @Override
    public List<Sucursal> getSucursales() {
        return sucursales.stream()
                .filter(d -> d.getCodigo().equals(this.codigo))
                .collect(Collectors.toList());
    }
}
