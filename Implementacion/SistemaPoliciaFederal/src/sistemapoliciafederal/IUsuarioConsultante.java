package sistemapoliciafederal;

import java.util.List;

public interface IUsuarioConsultante {

    public void setConsultaDelitos(IConsultaDelitos con);

    public List<IDelito> getDelitos();

    public void setConsultaSucursales(IConsultaSucursal con);

    public List<Sucursal> getSucursales();

    public void setConsultaBanco(IConsultaBanco con);

    public Banco getBanco();

    public void setConsultaContrato(IConsultaContrato con);

    public List<Contrato> getContratos();

    public void setConsultaDelincuente(IConsultaDelincuente con);

    public List<PersonaDetenida> getDelincuentes();
}
