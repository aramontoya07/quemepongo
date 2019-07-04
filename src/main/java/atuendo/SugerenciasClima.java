package atuendo;

import clima.Clima;
import usuario.PreferenciasDeAbrigo;
import usuario.Usuario;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SugerenciasClima {
    private Set<Atuendo> exactas = new HashSet<>();
    private Set<Atuendo> aproximadas = new HashSet<>();
    private int margen = 10;

    public SugerenciasClima(int margen) {
        this.margen = margen;
    }

    public void agregarAtuendoSegunClima(Atuendo atuendo, Clima climaActual){
        if(atuendo.nivelDeAdaptacionAlClima(climaActual) == 0) {
        	exactas.add(atuendo);
        	return;
        }
        if(Math.abs(atuendo.nivelDeAdaptacionAlClima(climaActual)) < margen) {
        	aproximadas.add(atuendo);
        }
    }

    public SugerenciasClima ajustarAGustos(PreferenciasDeAbrigo preferencias){
        exactas = exactas.stream().filter(atuendo -> atuendo.entraEnPreferencias(preferencias)).collect(Collectors.toSet());
        aproximadas = aproximadas.stream().filter(atuendo -> atuendo.entraEnPreferencias(preferencias)).collect(Collectors.toSet());
        return this;
    }

    public boolean esAptaParaClima(Clima clima){
        return exactas.stream().allMatch(exacta -> exacta.nivelDeAdaptacionAlClima(clima)==0) &&
                aproximadas.stream().allMatch(aproximada -> aproximada.nivelDeAdaptacionAlClima(clima) <= margen);
    }

    public Set<Atuendo> getExactas(){
        return exactas;
    }

    public Set<Atuendo> getAproximadas(){
        return exactas;
    }
}