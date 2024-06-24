package sistemapoliciafederal;

import java.io.IOException;
import java.time.*;
import java.util.*;

public class Control {

    private SistemaState sistemaState;
    private Usuario usuarioActual;

    public Control() {
        usuarioActual = null;
        sistemaState = SistemaState.newSistemaState(); //SINGLETON
    }

    public void RestaurarEstadoSistema() {
        try {
            this.sistemaState = sistemaState.deSerializar("PoliciaFederal.dat");

        } catch (IOException | ClassNotFoundException ex) {
            EntradaSalida.mostrarError("No hay datos para restaurar");
        }
    }

    public boolean Login() {
        String user = EntradaSalida.leerString("Ingrese su usuario");
        String pass = EntradaSalida.leerPassword("Ingrese su contraseña");
        for (Usuario u : this.sistemaState.getUsuarios()) {
            if (u.validarUsuarioContraseña(user, pass)) {
                EntradaSalida.mostrarString("Bienvenido al sistema de policia federal : " + user);

                //Solo a los usuario admin e investigador le inyecto el sistema state con todos los datos 
                if (u instanceof UsuarioAdmin) {
                    UsuarioAdmin ud = ((UsuarioAdmin) u);
                    ud.setSistemaState(this.sistemaState);
                    this.usuarioActual = ud;
                    return true;
                }

                if (u instanceof UsuarioInvestigador) {
                    UsuarioInvestigador ud = ((UsuarioInvestigador) u);
                    ud.setSistemaState(this.sistemaState);
                    this.usuarioActual = ud;
                    return true;

                }
                this.usuarioActual = u;

                return true;
            }
            EntradaSalida.mostrarString("Alguno de los datos ingresados son incorrectos");

        }
        EntradaSalida.mostrarString("Alguno de los datos ingresados son incorrectos");

        return false;
    }

    public void realizarAccionUsuario() {
        this.usuarioActual.accionar(); //POLIMORFISMO!!
    }

    public List<Usuario> GetUsuariosSistema() {
        return this.sistemaState.getUsuarios();
    }

    public void Desloguearse() {
        EntradaSalida.mostrarString("Gracias por haber utilizado el sistema de la policia Federal");
        //cuando me deslogeo guardo el estado del sistema
        if (this.usuarioActual instanceof UsuarioAdmin) {
            ((UsuarioAdmin) this.usuarioActual).serializar("UsuarioAdmin.bin");
        }
        this.usuarioActual = null;
    }

    public void dummyDeserializar() throws IOException, ClassNotFoundException {

        sistemaState = sistemaState.deSerializar("UsuarioAdmin.bin");
    }

    public void dummyTest() throws IOException {
        //  List<Juicio> juicios = new ArrayList<>();
        // List<Juez> jueces = new ArrayList<>();
        //   List<Vigilante> vigilantes = new ArrayList<>();
        //List<Banco> bancos = new ArrayList<>();
        // List<IDelito> delitos = new ArrayList<>();
        // List<Banda> bandas = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();

//        Date input = new Date();
//        Instant instant = input.toInstant();
//        Date hoy = Date.from(instant);

        //   PersonaDetenida d1 = new PersonaDetenida("111", "Juan 1");
        //  PersonaDetenida d2 = new PersonaDetenida("222", "Juan 2");
        //   PersonaDetenida d3 = new PersonaDetenida("333", "Juan 3");
        //   PersonaDetenida d4 = new PersonaDetenida("444", "Juan 4");
        //   Contrato c1 = new Contrato(hoy, true);
        //   Contrato c2 = new Contrato(hoy, true);
        //    Contrato c3 = new Contrato(hoy, false);
        //   Contrato c4 = new Contrato(hoy, true);
//
//        Vigilante v1 = new Vigilante("1111", 24, true, c1);
//        Vigilante v2 = new Vigilante("2222", 32, true, c2);
//        Vigilante v3 = new Vigilante("3333", 29, true, c1);
//        Vigilante v4 = new Vigilante("4444", 40, true, c3);
//        Vigilante v5 = new Vigilante("5555", 35, true, c4);
//        Vigilante v6 = new Vigilante("5555", 35, true);
//        Vigilante v7 = new Vigilante("5555", 35, true);
//        Vigilante v8 = new Vigilante("5555", 35, true);
//        Vigilante v9 = new Vigilante("5555", 35, true);
//
//        Juez juez1 = new Juez("clave 1", "juez 1", 2);
//        Juez juez2 = new Juez("clave 2", "juez 2", 3);
//        Juez juez3 = new Juez("clave 3", "juez 3", 5);
//        Juez juez4 = new Juez("clave 4", "juez 4", 7);
//        Juez juez5 = new Juez("clave 5", "juez 5", 5);
//        IDelito delito1 = new Delito(hoy, d1, true);
//        IDelito delito2 = new Delito(hoy, d2, false);
//        IDelito delito3 = new Delito(hoy, d3, false);
//        IDelito delito4 = new Delito(hoy, d4, true);
//
//        Juicio juicio1 = new Juicio(juez1, hoy, delito1, hoy, hoy);
//        Juicio juicio2 = new Juicio(juez2, hoy, delito2, hoy, hoy);
//        Juicio juicio3 = new Juicio(juez3, hoy, delito3, hoy, hoy);
//        Juicio juicio4 = new Juicio(juez4, hoy, delito4, hoy, hoy);
//
//        Sucursal suc1 = new Sucursal("111", "AAAA", 5);
//        suc1.NotificarDelito(hoy, d4);
//        suc1.GenerarContrato(true, hoy, v6);
//        suc1.GenerarContrato(true, hoy, v7);
//        suc1.GenerarContrato(true, hoy, v8);
//
//        Sucursal suc2 = new Sucursal("222", "BBBB", 15);
//        suc2.NotificarDelito(hoy, d3);
//        Sucursal suc3 = new Sucursal("333", "CCCC", 8);
//        Sucursal suc4 = new Sucursal("444", "DDDD", 10);
//        Sucursal suc5 = new Sucursal("555", "EEEE", 9);
//        suc5.NotificarDelito(hoy, d3);
//        suc5.GenerarContrato(true, hoy, v9);
//
//        Sucursal suc6 = new Sucursal("666", "fff", 19);
//        Banco banco1 = new Banco("111", "aaaa");
//        banco1.AddSucursal(suc1);
//        banco1.AddSucursal(suc2);
//        banco1.AddSucursal(suc3);
//
//        Banco banco2 = new Banco("222", "cccc");
//        banco1.AddSucursal(suc4);
//        banco1.AddSucursal(suc5);
//        banco1.AddSucursal(suc6);
//
//        Banda b1 = new Banda(123);
//        b1.addMiembro(d1);
//        b1.addMiembro(d2);
//        b1.addMiembro(d3);
//        Banda b2 = new Banda(345);
//        b2.addMiembro(d4);
SistemaState sist = SistemaState.newSistemaState();
        usuarios.add(UsuarioAdmin.nuevoUsuario("ADMIN", "ADMIN", sist));
//        usuarios.add(UsuarioAdmin.crearUsuario("bb", "bb", sistemaState));
//        usuarios.add(UsuarioInvestigador.crearUsuario("cc", "cc", sistemaState));
//        usuarios.add(UsuarioVigilante.crearUsuario("dd", "dd"));
////
//        jueces.add(juez1);
//        jueces.add(juez2);
//        jueces.add(juez3);
//        jueces.add(juez4);
//        jueces.add(juez5);
//
//        juicios.add(juicio1);
//        juicios.add(juicio2);
//        juicios.add(juicio3);
//        juicios.add(juicio4);
//
//        vigilantes.add(v1);
//        vigilantes.add(v2);
//        vigilantes.add(v3);
//        vigilantes.add(v4);
//        vigilantes.add(v5);
//
//        delitos.add(delito1);
//        delitos.add(delito2);
//        delitos.add(delito3);
//        delitos.add(delito4);
//
//        bancos.add(banco1);
//        bancos.add(banco2);
//
//        bandas.add(b2);
//        bandas.add(b1);
//
//        sistemaState.setJuicios(juicios);
//        sistemaState.setJueces(jueces);
//        sistemaState.setVigilantes(vigilantes);
//        sistemaState.setBancos(bancos);
//        sistemaState.setDelitos(delitos);
//        sistemaState.setBandas(bandas);
        sistemaState.setUsuarios(usuarios);

        sistemaState.serializar("PoliciaFederal.dat");

    }

}
