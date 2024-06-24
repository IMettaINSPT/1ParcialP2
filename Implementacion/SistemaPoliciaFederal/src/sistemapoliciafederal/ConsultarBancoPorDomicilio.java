package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConsultarBancoPorDomicilio implements IConsultaBanco, Serializable {

    private final List<Banco> bancos;
    private final String domicilio;

    public ConsultarBancoPorDomicilio(List<Banco> bancos, String domicilio) {
        this.bancos = bancos;
        this.domicilio = domicilio;
    }

    @Override
    public Banco getBanco() {
        List<Banco> lista = this.bancos.stream().filter(d -> d.getDomicilioCentral().equals(this.domicilio))
                .collect(Collectors.toList());
        if (Objects.nonNull(lista) && !lista.isEmpty()) {
            return lista.get(0);
        }
        return null;

    }

}
