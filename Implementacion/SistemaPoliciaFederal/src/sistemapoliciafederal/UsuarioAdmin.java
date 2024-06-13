/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

/**
 *
 * @author IMetta
 */
public class UsuarioAdmin extends Usuario implements IUsuarioConsultante {
   private static DB DB;
    
    @Override
    public int GetMenu() {
        return menu.mostrar("1-MostrarBanco 2-MostrarSucursales 3-MostrarDelincuentes 4-MostrarJuicios 5-MostrarDelitos 6-MostrarContrato 7-CargarBanco 8-CargarSucursal 9-CargarJuici 10-CargaBanda 11-CargarDelincuente 12-CargarVigilante 13-CargarDelito 14-CargarContrato 15-CargarJuez", "Error.Reintente", 1, 15, 3);
    }

    public void serializar() {
        DB.serializar();
    }
    
    public void deSerializar() {
        DB.serializar();
    }
    
    
    /*
       public void realizarTarea() {
        DB DB = new DB(); // O usar DB.deserializar(path) si ya tienes una base de datos guardada
        db.serializar("path/to/file.dat"); // Proporciona la ruta correcta del archivo
        for (Sucursal sucursal : DB.getSucursales()) {
            System.out.println(sucursal.getNombre());
        }
        for (Delito delito : DB.getDelitos()) {
            System.out.println(delito.getDescripcion());
        }
        for (Contrato contrato : DB.getContratos()) {
            System.out.println(contrato.getDetalles());
        }
    }
    */
    
    
    public static Usuario crearUsuario(String u, String p) {
        DB = new DB();
        Usuario user = new UsuarioAdmin();
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

        System.out.println("\nVigilantes:");
        for (Vigilante vigilante : DB.getVigilantes()) {
            System.out.println(vigilante);
        }

        System.out.println("\nJueces:");
        for (Juez juez : DB.getJueces()) {
            System.out.println(juez);
        }

        System.out.println("\nBandas:");
        for (Banda banda : DB.getBandas()) {
            System.out.println(banda);
        }
    }
}
