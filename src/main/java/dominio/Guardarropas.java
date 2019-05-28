package dominio;


import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
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
	public List<AtuendoBasico> generarSugerenciaBasica(){ //PARTE_SUP,PARTE_INF,CALZADO,ACCESORIO
			return Sets.cartesianProduct(basicas(superiores), basicas(inferiores), basicas(calzados)).stream()
					.map((list) -> new AtuendoBasico(list.get(0), list.get(1), list.get(2))).collect(Collectors.toList());	
	}
	
	public Set<Prenda> basicas(Set<Prenda> prendas){
		return prendas.stream().filter(prenda -> prenda.esBasica()).collect(Collectors.toSet());
	}

	public List<Atuendo> generarSugerencia(){
		List<AtuendoBasico> atuendosBasicos = generarSugerenciaBasica();
		List<Atuendo> sugerencias;
		atuendosBasicos.stream().map(atuendo -> combinarConSecundarios(atuendo)).collect(Collectors.toSet());
		return atuendosBasicos;
	}

	private Object combinarConSecundarios(AtuendoBasico atuendoBasico) {
		Set<Prenda> prendasSecundarias = atuendoBasico.prendasPermitidas(superiores,inferiores,calzados);
		prendasSecundarias.addAll(accesorios);
		Set<Set<Prenda>> combinacionesSecundarias = Sets.powerSet(prendasSecundarias);
		combinacionesSecundarias.stream().map(prendasSecundarias -> atuendoBasico.agregarPrendas(prenda))
		return null;
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



