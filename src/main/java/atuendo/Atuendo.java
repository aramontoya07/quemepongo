package atuendo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

import clima.Clima;
import excepciones.AbrigoException;
import excepciones.CategoriaOcupadaException;
import prenda.Categoria;
import prenda.ParteAbrigada;
import prenda.Prenda;
import usuario.Guardarropas;
import usuario.PreferenciasDeAbrigo;

import static prenda.ParteAbrigada.*;

public class Atuendo {
	private Guardarropas guardarropasOrigen;
	private Prenda superior;
	private Prenda inferior;
	private Prenda calzado;
	private List<Prenda> accesorios = new ArrayList<Prenda>();
	private List<Prenda> capasAbrigos = new ArrayList<Prenda>();
	private Integer rangoDeAceptacion = 10;

	public Guardarropas getGuardarropasOrigen() {
		return guardarropasOrigen;
	}

	public Prenda getSuperior() {
		return superior;
	}

	public Prenda getInferior() {
		return inferior;
	}

	public Prenda getCalzado() {
		return calzado;
	}

	public List<Prenda> getAccesorios() {
		return accesorios;
	}

	public List<Prenda> getCapasAbrigos() {
		return capasAbrigos;
	}

	public Atuendo(Prenda pSuperior, Prenda pInferior, Prenda pCalzado) {
		superior = pSuperior;
		inferior = pInferior;
		calzado = pCalzado;
	}

	public boolean aceptaSuperponer(Prenda prenda){
		switch (prenda.getCategoria()){
			case PARTE_SUPERIOR:
				return ultimoSuperior().aceptaSuperponerPrenda(prenda);
			case ACCESORIO:
				return ultimoAccesorio().aceptaSuperponerPrenda(prenda);
		}
		return false;
	}

	public void agregarAbrigo(Prenda prenda){
		switch (prenda.getCategoria()){
			case PARTE_SUPERIOR:
				capasAbrigos.add(prenda);
				break;
			case ACCESORIO:
				accesorios.add(prenda);
				break;
		}
	}

	private Prenda ultimoAccesorio() {
		return accesorios.get(accesorios.size()-1);
	}

	private Prenda ultimoSuperior() {
		if(capasAbrigos.isEmpty()){
			return superior;
		}else{
			return capasAbrigos.get(capasAbrigos.size()-1);
		}
	}

	private int nivelAbrigo() {
		return superior.nivelAbrigo() +
			   inferior.nivelAbrigo() +
			   calzado.nivelAbrigo() +
			   accesorios.stream().mapToInt(Prenda::nivelAbrigo).sum() +
			   capasAbrigos.stream().mapToInt(Prenda::nivelAbrigo).sum();
	}

	double nivelDeAdaptacionAlClima(Clima climaActual){
		return this.nivelAbrigo() - climaActual.nivelAbrigoRequerido();
	}

	public List<Prenda> obtenerPrendasTotales(){
		List<Prenda> prendasTotales = new ArrayList<Prenda>();
		prendasTotales.add(superior);
		prendasTotales.add(inferior);
		prendasTotales.add(calzado);
		prendasTotales.addAll(capasAbrigos);
		prendasTotales.addAll(accesorios);
		return prendasTotales;
	}

	public void liberar() {
		obtenerPrendasTotales().forEach(prenda -> guardarropasOrigen.liberarPrenda(prenda));
	}

	public PreferenciasDeAbrigo generarPreferencias() {
		PreferenciasDeAbrigo preferencias = new PreferenciasDeAbrigo();
		preferencias.setAbrigoCabeza(abrigoEn(CABEZA));
		preferencias.setAbrigoCuello(abrigoEn(CUELLO));
		preferencias.setAbrigoManos(abrigoEn(MANOS));
		preferencias.setAbrigoPecho(abrigoEn(PECHO));
		preferencias.setAbrigoPiernas(abrigoEn(PIERNAS));
		preferencias.setAbrigoPies(abrigoEn(PIES));
		return preferencias;
	}

	private Integer abrigoEn(ParteAbrigada parte) {
		return obtenerPrendasTotales().stream().mapToInt(prenda-> (int) prenda.abrigoEnParte(parte)).sum();
	}

	private boolean entraEnRango(Integer abrigo,Integer nivelDePreferencia){
		return abrigo <= nivelDePreferencia + rangoDeAceptacion &&
				abrigo >= nivelDePreferencia - rangoDeAceptacion;
	}

    public boolean entraEnPreferencias(PreferenciasDeAbrigo preferencias) {
		return entraEnRango(abrigoEn(CABEZA),preferencias.getAbrigoCabeza()) &&
			entraEnRango(abrigoEn(CUELLO),preferencias.getAbrigoCuello()) &&
			entraEnRango(abrigoEn(PECHO),preferencias.getAbrigoPecho()) &&
			entraEnRango(abrigoEn(PIERNAS),preferencias.getAbrigoPiernas()) &&
			entraEnRango(abrigoEn(PIES),preferencias.getAbrigoPies()) &&
			entraEnRango(abrigoEn(MANOS),preferencias.getAbrigoManos());

    }

	public void setRangoDeAceptacion(Integer rangoDeAceptacion) {
		this.rangoDeAceptacion = rangoDeAceptacion;
	}
}