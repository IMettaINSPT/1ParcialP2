package sistemapoliciafederal;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConsultarBancoPorCodigo implements IConsultaBanco, Serializable {

    private final List<Banco> bancos;
    private final String codigo;

    public ConsultarBancoPorCodigo(List<Banco> bancos, String codigo) {
        this.bancos = bancos;
        this.codigo = codigo;
    }

    @Override
    public Banco getBanco() {
        List<Banco> lista  =this.bancos.stream().filter(d -> d.getCodigoBanco().equals(this.codigo))
                .collect(Collectors.toList());
        if(Objects.nonNull(lista) && !lista.isEmpty()){return lista.get(0);}
        return null;
    }

}
