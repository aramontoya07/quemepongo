package atuendo;

import java.util.HashSet;
import java.util.Iterator;
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
<<<<<<< HEAD
	AtuendoBasico capaBasica;
	Guardarropas guardarropasOrigen;
	Set<CapaDeAbrigo> capasAbrigos = new HashSet<CapaDeAbrigo>();
=======
	private Prenda superior;
	private Prenda inferior;
	private Prenda calzado;
	private Set<Prenda> accesorios = new HashSet<Prenda>();
	SortedSet<Prenda> capasAbrigos = (SortedSet<Prenda>) new HashSet<Prenda>();
	
>>>>>>> 1ee96c2afc3aa66b51fe2e23a2a944d4c15a8409

	public Atuendo(Prenda pSuperior, Prenda pInferior, Prenda pCalzado) {
		superior = pSuperior;
		inferior = pInferior;
		calzado = pCalzado;
	}

	public void agregarAbrigo(Prenda capa) {
		if(capasAbrigos.last().aceptaSuperponerPrenda(capa)) {
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

	public boolean esAtuendoValido(Atuendo atuendo) {
		return true;
	}

//	public void agregarPrendas(Set<Prenda> prendas) {
//		if (prendas.isEmpty()) {
//			return;
//		}
//		prendas.stream().forEach(prenda -> agregarPrenda(prenda));
//	}
//
//	public void agregarPrenda(Prenda prenda) {
//		Iterator <CapaDeAbrigo> iterator = capasAbrigos.iterator();
//		while(iterator.hasNext()){
//			try {
//				iterator.next().agregarPrenda(prenda);
//				return;
//			} catch (CategoriaOcupadaException e){
//				continue;
//			}
//		}
//		CapaDeAbrigo capaNueva = new CapaDeAbrigo();
//		capaNueva.agregarPrenda(prenda);
//		agregarCapa(capaNueva);
//	}

	public double nivelDeAdaptacionAlClima(Clima climaActual){
		return this.nivelAbrigo() - climaActual.nivelAbrigoRequerido();
	}
	
	public void liberar() {
		
	}
}