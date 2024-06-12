/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.Serializable;

public abstract class Usuario implements Serializable  {

    private String user, pass;         

    protected Menu menu = new Menu() ;

    public abstract int GetMenu();
    
    public boolean Login(String usuario, String contraseña)
    {
       if(!(user.equals(usuario) || pass.equals(contraseña)) )
       {
           System.out.println("Algunos de los datos nos incorrectos.Vuelva a ingresarlos");
           return false;
       }
       return true;
    }
    
     public void Desloguearse()
    {
        System.out.println("Gracias por haber utilizado el sistema de la policia Federal");
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
