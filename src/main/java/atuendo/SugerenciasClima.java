package atuendo;

import clima.Clima;
import clima.ServicioClimatico;
import usuario.EntidadPersistente;
import usuario.PreferenciasDeAbrigo;
import usuario.Usuario;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
@Entity
public class SugerenciasClima extends EntidadPersistente{
	@Transient
    private List<Atuendo> exactas = new ArrayList<>();
	@Transient
    private List<Atuendo> aproximadas = new ArrayList<>();
	@ManyToMany
	private List<Atuendo> atuendos = new ArrayList<>();
    private int margen = 10;

    public SugerenciasClima(int margen) {
        this.margen = margen;
    }

    public void agregarAtuendoSegunClima(Atuendo atuendo, Clima climaActual){
        if(atuendo.nivelDeAdaptacionAlClima(climaActual) == 0) {
        	exactas.add(atuendo);
        	atuendo.setAproximado(false);
        	return;
        }
        if(Math.abs(atuendo.nivelDeAdaptacionAlClima(climaActual)) < margen) {
        	aproximadas.add(atuendo);
        	atuendo.setAproximado(true);
        } //falta hacer el setAtuendos
    }

    public void setAtuendos() {
        ArrayList<Atuendo> listaAux = new ArrayList<>();
        listaAux.addAll(exactas);
        listaAux.addAll(aproximadas);
        atuendos = listaAux;
    }

    public SugerenciasClima ajustarAGustos(PreferenciasDeAbrigo preferencias, double temperatura){
        exactas = exactas.stream().sorted(Comparator.comparing(atuendo -> preferencias.obtenerNivelDeAdaptacion(temperatura, atuendo))).collect(Collectors.toList()); //deberia ordenar los atuendos segun su nivel de adaptacion de menor a mayor
        aproximadas = aproximadas.stream().sorted(Comparator.comparing(atuendo -> preferencias.obtenerNivelDeAdaptacion(temperatura, atuendo))).collect(Collectors.toList());
        exactas.forEach(atuendo -> atuendo.setTemperaturaDeUso(temperatura));
        aproximadas.forEach(atuendo -> atuendo.setTemperaturaDeUso(temperatura));
        return this;
    }

    public boolean esAptaParaClima(Clima clima){
        return exactas.stream().allMatch(exacta -> exacta.nivelDeAdaptacionAlClima(clima)==0) &&
                aproximadas.stream().allMatch(aproximada -> aproximada.nivelDeAdaptacionAlClima(clima) <= margen);
    }

    public List<Atuendo> getExactas(){
        return exactas;
    }

    public List<Atuendo> getAproximadas(){
        return aproximadas;
    }
}