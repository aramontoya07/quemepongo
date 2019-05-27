package dominio;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Guardarropas {
	
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
		return Sets.cartesianProduct(superiores, inferiores, calzados, accesorios).stream()
				.map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), list.get(3))).collect(Collectors.toList());	
	}

	public int cantidadDePrendas() {
		return superiores.size() + inferiores.size() + calzados.size() + accesorios.size();
	}

	public void agregarPrenda(Prenda prenda) {
		switch (prenda.getCategoria()) {
		case PARTE_SUPERIOR:
			superiores.add(prenda);
			break;
		case PARTE_INFERIOR:
			inferiores.add(prenda);
			break;
		case CALZADO:
			calzados.add(prenda);
			break;
		case ACCESORIO:
			accesorios.add(prenda);
			break;
		}
	}
	
}



