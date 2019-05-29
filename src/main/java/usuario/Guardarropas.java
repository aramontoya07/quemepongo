package usuario;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import atuendo.Atuendo;
import atuendo.AtuendoBasico;
import clima.ServicioClimatico;
import prenda.Prenda;

public class Guardarropas {
	
	public Set<Prenda> superiores = new HashSet<Prenda>();
	public Set<Prenda> inferiores = new HashSet<Prenda>();
	public Set<Prenda> calzados = new HashSet<Prenda>();
	public Set<Prenda> accesorios = new HashSet<Prenda>();
	
	public Guardarropas() {

	}

	@SuppressWarnings("unchecked")
	public Set<AtuendoBasico> generarSugerenciaBasica(){
			return Sets.cartesianProduct(prendasPrimarias(superiores), prendasPrimarias(inferiores), prendasPrimarias(calzados)).stream()
					.map((list) -> new AtuendoBasico(list.get(0), list.get(1), list.get(2))).collect(Collectors.toSet());
	}
	
	public Set<Prenda> prendasPrimarias(Set<Prenda> prendas){
		return prendas.stream()
				.filter(prenda -> prenda.esPrimaria()).collect(Collectors.toSet());
	}

	public Set<Atuendo> generarSugerencias(){
		Set<AtuendoBasico> atuendosBasicos = generarSugerenciaBasica();
		return atuendosBasicos.stream()
				.map(atuendo -> combinarConSecundarios(atuendo))
				.flatMap(atuendos -> atuendos.stream()).collect(Collectors.toSet());
	}

	public Set<Atuendo> combinarConSecundarios (AtuendoBasico atuendoBasico) {
		Set<Prenda> prendasSecundarias = atuendoBasico.prendasPermitidas(superiores,inferiores,calzados);
		prendasSecundarias.addAll(accesorios);
		Set<Set<Prenda>> combinacionesSecundarias = Sets.powerSet(prendasSecundarias);
		return combinacionesSecundarias.stream()
				.map(prendas -> new Atuendo(atuendoBasico,prendas)).collect(Collectors.toSet());
	}

	public Set<Atuendo> generarSugerenciasSegunClima(ServicioClimatico provedorElegido){
		return generarSugerencias().stream()
				.filter(sugerencia -> sugerencia.soportaClima(provedorElegido.obtenerClima())).collect(Collectors.toSet());
	}
	
	public int cantidadDePrendas() {
		return superiores.size() + inferiores.size() + calzados.size() + accesorios.size();
	}

	public void agregarPrendas(Set<Prenda> prendas) {
		prendas.parallelStream().forEach(prenda -> agregarPrenda(prenda));
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



