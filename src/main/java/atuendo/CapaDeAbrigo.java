package atuendo;

import java.util.HashSet;
import java.util.Set;

import excepciones.CategoriaOcupadaException;
import prenda.Categoria;
import prenda.Prenda;

public class CapaDeAbrigo {
	// Una prenda de cada categoria por definicion
	// Antes de agregar una prenda se pregunta si puede tener ese tipo de prenda.

	Set<Prenda> prendas = new HashSet<Prenda>();

	public CapaDeAbrigo() {

	}

	public Prenda getPrendaDeCategoria(Categoria categoria){
		Prenda prendaNula = new Prenda(null,null,null,null,null);
		return prendas.stream().findFirst().filter(prenda->prenda.esDeCategoria(categoria)).orElse(prendaNula);
	}

	public int nivelAbrigo() {
		return prendas.stream().mapToInt(prendas -> prendas.nivelAbrigo()).sum();
	}

	public boolean tieneParte(Categoria categoria) {
		return prendas.stream().anyMatch(prendas -> prendas.esDeCategoria(categoria));
	}

	public void agregarPrenda(Prenda prenda) {
		if(tieneParte(prenda.getCategoria())) throw new CategoriaOcupadaException();
		prendas.add(prenda);
	}

}
