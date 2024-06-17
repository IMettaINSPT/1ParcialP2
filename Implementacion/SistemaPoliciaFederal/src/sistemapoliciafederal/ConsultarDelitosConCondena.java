/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author IMetta
 */
public class ConsultarDelitosConCondena implements IConsultaDelitos {

    private final List<Delito> delitos;

    public ConsultarDelitosConCondena(List<Delito> delitos) {
        this.delitos = delitos;
    }

    @Override
    public List<IDelito> getDelitos() {
        return  delitos.stream().filter(d -> d.getConCondena()).collect(Collectors.toList());
    }

}
