package atuendo;

import clima.Clima;
import db.EntidadPersistente;
import usuario.PreferenciasDeAbrigo;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name = "Sugerencias")
public class SugerenciasClima extends EntidadPersistente{

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id_sugerencia")
    private List<SugerenciasPosibles> atuendosSugeridos = new ArrayList<SugerenciasPosibles>();
    
    private int margen = 1000;

    public SugerenciasClima() {
    }

    public boolean esAptaParaClima(Clima clima){
        return atuendosSugeridos.stream().allMatch(sugerencia -> sugerencia.getAtuendo().nivelDeAdaptacionAlClima(clima) <= margen);
    }

    public void setMargen(int unMargen){
        margen = unMargen;
    }

    public List<Atuendo> getExactas(){
        return atuendosSugeridos.stream().filter(sugerencia -> sugerencia.getTipo().equals(TipoSugerencia.EXACTA)).map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toList());
    }

    public List<Atuendo> getAproximadas(){
        return atuendosSugeridos.stream().filter(sugerencia -> sugerencia.getTipo().equals(TipoSugerencia.APROXIMADA)).map(sugerencia -> sugerencia.getAtuendo()).collect(Collectors.toList());
    }

    public void agregarAtuendoSegunClima(Atuendo atuendo, Clima climaActual){
        if(atuendo.nivelDeAdaptacionAlClima(climaActual) == 0) {
            atuendosSugeridos.add(new SugerenciasPosibles(atuendo,TipoSugerencia.EXACTA));
        	return;
        }
        if(Math.abs(atuendo.nivelDeAdaptacionAlClima(climaActual)) < margen) {
            atuendosSugeridos.add(new SugerenciasPosibles(atuendo,TipoSugerencia.APROXIMADA));
            return;
        }
    }

    public SugerenciasClima ajustarAGustos(PreferenciasDeAbrigo preferencias, double temperatura){
        atuendosSugeridos = atuendosSugeridos.stream()
        .sorted(Comparator.comparing(atuendoSugerido -> 
            preferencias.obtenerNivelDeAdaptacion(temperatura, atuendoSugerido.getAtuendo())))
            .collect(Collectors.toList());
        return this;
    }
}