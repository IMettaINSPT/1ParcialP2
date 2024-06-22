package sistemapoliciafederal;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultarBancoPorCodigo implements IConsultaBanco {
    private final List<Banco> bancos;
    private final String codigo;

    public ConsultarBancoPorCodigo(List<Banco> bancos, String codigo) {
        this.bancos = bancos;
        this.codigo = codigo;
    }

    @Override
    public Banco getBanco() {
         return this.bancos.stream().filter(d-> d.getCodigoBanco().equals(this.codigo))
                 .collect(Collectors.toList()).get(0);
    }    
    
}


