package atuendo;

import clima.Clima;

import java.util.HashSet;
import java.util.Set;

public class SugerenciasClima {
    private Set<Atuendo> exactas = new HashSet<>();
    private Set<Atuendo> aproximadas = new HashSet<>();
    private int margen = 10;

    public SugerenciasClima(int margen) {
        this.margen = margen;
    }

    public void agregarAtuendoSegunClima(Atuendo atuendo, Clima climaActual){
        if(atuendo.nivelDeAdaptacionAlClima(climaActual) == 0) exactas.add(atuendo);
        if(Math.abs(atuendo.nivelDeAdaptacionAlClima(climaActual)) < margen) aproximadas.add(atuendo);
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