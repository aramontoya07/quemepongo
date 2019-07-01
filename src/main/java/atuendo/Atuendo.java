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
import prenda.Prenda;
import usuario.Guardarropas;

public class Atuendo {
	Guardarropas guardarropasOrigen;
	private Prenda superior;
	private Prenda inferior;
	private Prenda calzado;
	private List<Prenda> accesorios = new ArrayList<Prenda>();
	private List<Prenda> capasAbrigos = new ArrayList<Prenda>();
	


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
	
	private void agregarLosQueAcepta(Set<Prenda> setAManipular, Set<Prenda> prendasAEvaluar, Prenda prendaCriterio){
		setAManipular.addAll(prendasAEvaluar.stream().filter(prenda -> prendaCriterio.aceptaSuperponerPrenda(prenda))
				.collect(Collectors.toSet()));
	}
	
	public Set<Prenda> prendasPermitidas(Set<Prenda> superiores) {
		Set<Prenda> setPermitido = new HashSet<Prenda>();
		agregarLosQueAcepta(setPermitido, superiores, superior);
		return setPermitido;
	}

	public void agregarAbrigo(Prenda capa) {
		if(capasAbrigos.get(capasAbrigos.size()-1).aceptaSuperponerPrenda(capa)) {
			capasAbrigos.add(capa);
		}else {
			throw new AbrigoException();
		}
	}

	public int nivelAbrigo() {
		return superior.nivelAbrigo() +
			   inferior.nivelAbrigo() +
			   calzado.nivelAbrigo() +
			   accesorios.stream().mapToInt(accesorio -> accesorio.nivelAbrigo()).sum() +
			   capasAbrigos.stream().mapToInt(capa -> capa.nivelAbrigo()).sum();
	}

	public boolean esAtuendoValido() {
		return (tieneAbrigosValidos() && tieneAccesoriosValidos()); 	
	}

	private boolean tieneAbrigosValidos() {
		return esConjuntoValido(superior,capasAbrigos);
	}

	private boolean tieneAccesoriosValidos() {
		if(accesorios.isEmpty()) return true;
		else return esConjuntoValido(accesorios.get(0),accesorios);
	}

	private boolean esConjuntoValido(Prenda prenda, List<Prenda> prendas) {
		int indice = 0;
		if(prendas.isEmpty()) {
			return true;
		}else{
			if(prenda.aceptaSuperponerPrenda(prendas.get(indice))) {
				if(sonPrendasValidas(prendas,indice+1)) return true;
			}
		}
		return false;
	}

	private boolean sonPrendasValidas(List<Prenda> prendas, int indice) {
		if(prendas.get(indice).equals(null)) {
			return true;
		}else {
			if(sonPrendasValidas(prendas, indice+1)) return true;
		}
		return false;
	}

	public double nivelDeAdaptacionAlClima(Clima climaActual){
		return this.nivelAbrigo() - climaActual.nivelAbrigoRequerido();
	}
	
	public void liberar() {
		
	}

	public void ponerAbrigos(List<Prenda> abrigosOrd) {
		capasAbrigos = abrigosOrd;
		
	}

	public void ponerAccesorios(List<Prenda> accesoriosOrd) {
		accesorios = accesoriosOrd;
		
	}

}