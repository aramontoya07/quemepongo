package atuendo;

import java.util.HashSet;
import java.util.Set;

import prenda.Categoria;
import prenda.Prenda;

public class CapaDeAbrigo {
	// Una prenda de cada categoria por definicion
	// Antes de agregar una prenda se pregunta si puede tener ese tipo de prenda.

	Set<Prenda> prendas = new HashSet<Prenda>();

	public CapaDeAbrigo() {

	}

	public int nivelAbrigo() {
		return prendas.stream().mapToInt(prendas -> prendas.nivelAbrigo()).sum();
	}

	public boolean noTieneParte(Categoria categoria) {
		return prendas.stream().anyMatch(prendas -> prendas.esDeCategoria(categoria));
	}

	public void agregarPrenda(Prenda prenda) {
		prendas.add(prenda);
	}

}
