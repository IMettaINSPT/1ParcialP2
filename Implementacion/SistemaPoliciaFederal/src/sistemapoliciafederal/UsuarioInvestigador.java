/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class UsuarioInvestigador extends Usuario implements IUsuarioConsultante {

    private static DB DB;
    
    
    public UsuarioInvestigador() {
    }

    @Override
    public int GetMenu() {
        return menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato", "Error.Reintente", 1, 6, 3);
    }   
    
    public static Usuario crearUsuario(String u, String p) {
        Usuario user = new UsuarioInvestigador();
        user.setPass(p);
        user.setUser(u);
        return user;
    }
    
       public void consultar() {
       
        if (DB == null) {
            System.out.println("La base de datos no está inicializada.");
            return;
        }

        // Imprimir la información de todas las listas en la base de datos.
        System.out.println("Bancos:");
        for (Banco banco : DB.getBancos()) {
            System.out.println(banco);
        }

        System.out.println("\nSucursales:");
        for (Sucursal sucursal : DB.getSucursales()) {
            System.out.println(sucursal);
        }

        System.out.println("\nDelincuentes:");
        for (PersonaDetenida delincuente : DB.getDelincuentes()) {
            System.out.println(delincuente);
        }

        System.out.println("\nJuicios:");
        for (Juicio juicio : DB.getJuicios()) {
            System.out.println(juicio);
        }

        System.out.println("\nDelitos:");
        for (Delito delito : DB.getDelitos()) {
            System.out.println(delito);
        }

        System.out.println("\nContratos:");
        for (Contrato contrato : DB.getContratos()) {
            System.out.println(contrato);
        }
    }
}
