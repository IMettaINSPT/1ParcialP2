package sistemapoliciafederal;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultarBancoPorDomicilio implements IConsultaBanco {

    private List<Banco> bancos;
    private String domicilio;

    public ConsultarBancoPorDomicilio(List<Banco> bancos, String domicilio) {
        this.bancos = bancos;
        this.domicilio = domicilio;
    }

    @Override
    public Banco getBanco() {
        return this.bancos.stream().filter(d -> d.getDomicilioCentral().equals(this.domicilio))
                .collect(Collectors.toList()).get(0);
    }

}


