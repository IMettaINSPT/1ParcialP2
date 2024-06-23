package sistemapoliciafederal;

import java.util.List;

public class UsuarioVigilante extends Usuario {

    private Vigilante vigilante;
    private IConsultaContrato consultaContrato;

    @Override
    public int GetMenu() {
        return Menu.mostrar("1-MostrarContrato", "Error.Reintente", 1, 1, 3);
    }

    @Override
    public void accionar() {
        this.setConsultaContrato(new ConsultarContratosVigilante(vigilante));
        switch (this.GetMenu()) {
            case 1:
                for (Contrato c : this.getContratos()) {
                    c.getInfoContrato();
                }
        }
    }

    public static Usuario nuevoUsuario(String u, String p) {
        UsuarioVigilante user = new UsuarioVigilante();
        user.setPass(p);
        user.setUser(u);
        return user;
    }

    public Vigilante getVigilanteAsociado() {
        return vigilante;
    }

    public void setVigilanteAsociado(Vigilante v) {
        this.vigilante = v;
    }

    @Override
    public void getInfoGeneral() {
        System.out.println("\nVigilante:");
        System.out.println(vigilante.getInfoVigilante());
    }

    public void setConsultaContrato(IConsultaContrato con) {
        this.consultaContrato = con;
    }

    public List<Contrato> getContratos() {
        return this.consultaContrato.getContratos();
    }

}
