package sistemapoliciafederal;

import java.io.Serializable;
import java.util.Objects;

public abstract class Usuario implements Serializable {

    private String user, pass;

    protected Menu menu = new Menu();

    // Constructor 
    public Usuario(String username, String password) {
        this.user = username;
        this.pass = password;
    }

    public abstract int GetMenu();

    public boolean validarUsuarioContraseña(String usuario, String contraseña) {
        return ((this.user.equals(usuario) && this.pass.equals(contraseña)));
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

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Usuario) {
            Usuario u = (Usuario) obj;
            return u.getPass().equals(this.getPass()) && u.getUser().equals(this.getUser());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.user);
        hash = 31 * hash + Objects.hashCode(this.pass);
        return hash;
    }

    public abstract void getInfoGeneral();

    public abstract void mostrarMenu();
}
