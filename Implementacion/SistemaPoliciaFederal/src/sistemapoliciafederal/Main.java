/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemapoliciafederal;


/**
 *
 * @author IMetta
 */
public class Main {
     private static Usuario  userAdm = null  ;
     private static Usuario userInv = null  ;
     private static  Usuario userVig = null  ;

  //  Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Test");   
        crearUsuario(userAdm);
        
    }    
    
    public static void crearUsuario(Usuario user)
    {
        int tipoUsuario = Menu.mostrar("1- ADMIN 2-INVESTIGADOR 3-VIGILANTE 4-SALIR", "Opcion incorrecta", 1, 4, 3);
        
        if( tipoUsuario != -1)
        {
        String usuario = "";
        String pass = "";        
            switch(tipoUsuario)
            {
                case 1: user = UsuarioAdmin.crearUsuario(usuario,pass);   break;                
                case 2:  user = UsuarioInvestigador.crearUsuario(usuario,pass); break;
                case 3:  user = UsuarioVigilante.crearUsuario(usuario,pass);  break;
                case 4: System.out.println("Adios"); break;
            } 
        }
    }

}
