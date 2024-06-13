/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public abstract class Usuario {

    private String user, pass;         

    protected Menu menu = new Menu();

    public abstract int GetMenu();
    
    public boolean Login(String usuario, String contraseña) {
        if (!(user.equals(usuario) || pass.equals(contraseña))) {
            System.out.println("Algunos de los datos son incorrectos. Vuelva a ingresarlos");
            return false;
        }
        return true;
    }
    
    public void Desloguearse() {
        System.out.println("Gracias por haber utilizado el sistema de la policía Federal");
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
