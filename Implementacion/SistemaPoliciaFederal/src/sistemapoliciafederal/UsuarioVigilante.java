/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;


public class UsuarioVigilante extends Usuario {

    private Vigilante vigilante;

    public UsuarioVigilante() {
        super();
    }

    @Override
    public int getMenu() {
        return menu.mostrar("1-MostrarContrato", "Error. Reintente", 1, 1, 3);
    }

    public static Usuario crearUsuario(String u, String p) {
        UsuarioVigilante user = new UsuarioVigilante();
        user.setPass(p);
        user.setUser(u);
        return user;
    }

    /**
     * Método que permite consultar los datos del contrato del vigilante.
     * @return String con la información del contrato.
     */
    public String consulta() {
        if (vigilante != null && vigilante.getContrato() != null) {
            Contrato contrato = vigilante.getContrato();
            return "Contrato del vigilante:\n" +
                   "Fecha del contrato: " + contrato.getFechaContrato() + "\n" +
                   "Usa arma: " + (contrato.isConArma() ? "Sí" : "No") + "\n";
        } else {
            return "No se encontró información del contrato del vigilante.";
        }
    }

    public void setVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

    public Vigilante getVigilante() {
        return vigilante;
    }
}
