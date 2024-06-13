/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author IMetta
 */
public class DB implements Serializable {
    private List<Juicio> juicios;
    private List<Vigilante> vigilantes;
    private List<Banco> bancos;
    private List<Juez> jueces;
    private List<PersonaDetenida> delincuentes;
    private List<Banda> bandas;
    private List<Sucursal> sucursales;
    private List<Delito> delitos;
    private List<Contrato> contratos;

    public DB() {
        juicios = new ArrayList<>();
        vigilantes = new ArrayList<>();
        bancos = new ArrayList<>();
        jueces = new ArrayList<>();
        delincuentes = new ArrayList<>();
        bandas = new ArrayList<>();
        sucursales = new ArrayList<>();
        delitos = new ArrayList<>();
        contratos = new ArrayList<>();
    }

    // Métodos para obtener las listas
    public List<Juicio> getJuicios() {
        return juicios;
    }

    public List<Vigilante> getVigilantes() {
        return vigilantes;
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public List<Juez> getJueces() {
        return jueces;
    }

    public List<PersonaDetenida> getDelincuentes() {
        return delincuentes;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public List<Delito> getDelitos() {
        return delitos;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    // Métodos para serializar y deserializar
    public void serializar(String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(this);
            System.out.println("Datos serializados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al serializar los datos: " + e.getMessage());
        }
    }

    public static DB deserializar(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return (DB) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar los datos: " + e.getMessage());
            return new DB(); // En caso de error, devolver una nueva instancia vacía
        }
    }
}


