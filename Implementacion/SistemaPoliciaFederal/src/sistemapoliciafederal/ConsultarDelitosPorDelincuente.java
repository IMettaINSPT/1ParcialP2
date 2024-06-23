/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author IMetta
 */
public class ConsultarDelitosPorDelincuente implements IConsultaDelitos {

    private final PersonaDetenida delincuente;
    private final List<IDelito> delitos;

    public ConsultarDelitosPorDelincuente(List<IDelito> delitos, PersonaDetenida delincuente) {
        this.delincuente = delincuente;
        this.delitos = delitos;
    }

    @Override
    public List<IDelito> getDelitos() {

        return this.delitos.stream().filter(d -> d.getDelincuente().soyDelincuente(delincuente))
                .collect(Collectors.toList());

    }

}
