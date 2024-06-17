/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemapoliciafederal;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultarDelitosPorBanda implements IConsultaDelitos {
    private final Banda banda ;
    private final List<Delito> delitos;
    
    // la referencia a la banda y la lista de delitos me viene inyectada del llamador (en este sist sistemaState las contiene)
    public ConsultarDelitosPorBanda(Banda banda , List<Delito> delitos)
    {
        this.banda = banda;
        this.delitos = delitos;        
    }
    
    @Override
    public List<IDelito> getDelitos() {
        List<IDelito> delitosFinal = new ArrayList<>();
        for(PersonaDetenida delincuente : this.banda.getMiembros())
        {
            delitosFinal.addAll(
                    this.delitos.stream()
                     .filter(delito -> delito.getDelincuente().getCodigo().equals(delincuente.getCodigo()))
                     .collect(Collectors.toList())            
            );
        }
        return delitosFinal;
    }

}
