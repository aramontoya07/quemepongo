package quemepongo;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Guardarropas { //Usuario.
	
	public Set<Prenda> superiores;
	public Set<Prenda> inferiores;
	public Set<Prenda> calzados;
	public Set<Prenda> accesorios;
	
	public Guardarropas(Set<Prenda> superiores, Set<Prenda> inferiores, Set<Prenda> calzados, Set<Prenda> accesorios) {
		this.superiores = superiores;
		this.inferiores = inferiores;
		this.calzados = calzados;
		this.accesorios = accesorios;
	}

	@SuppressWarnings("unchecked")
	public List<Atuendo> generarSugerencia() {
		return Sets.cartesianProduct(superiores, inferiores, calzados,accesorios).stream()
				.map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3))).collect(Collectors.toList());
	}
}



