package usuario;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.SugerenciasClima;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.mchange.v2.codegen.bean.PropsToStringGeneratorExtension;

import atuendo.Atuendo;
import atuendo.AtuendoBasico;
import clima.ServicioClimatico;
import excepciones.NoExistePrendaEnGuardarropaException;
import excepciones.PrendaYaExisteException;
import prenda.*;

public class Guardarropas {

	public Set<Prenda> superiores = new HashSet<Prenda>();
	public Set<Prenda> inferiores = new HashSet<Prenda>();
	public Set<Prenda> calzados = new HashSet<Prenda>();
	public Set<Prenda> accesorios = new HashSet<Prenda>();
	
	public Set<Prenda> prendasUsada = new HashSet<Prenda>();
	
	public Guardarropas() { }

	public void usarPrenda(Prenda prenda) {
		if(!existePrenda(prenda)) return; //TIRAR EXCEP
		prendasUsada.add(prenda);
	}
	
	public void liberarPrenda(Prenda prenda) {
		if(!existePrenda(prenda)) return; //TIRAR EXCEP
		prendasUsada.remove(prenda);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Atuendo> generarSugerenciaBasica(){
		return Sets
				.cartesianProduct(prendasPrimarias(superiores), inferiores, calzados)
				.stream().map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2)))
				.collect(Collectors.toSet());
	}

	public Set<Prenda> prendasPrimarias(Set<Prenda> prendas){
		return prendas.stream().filter(prenda -> prenda.esPrimaria()).collect(Collectors.toSet());
	}

	public Set<Atuendo> generarSugerenciasPosibles(){
		Set<Atuendo> atuendosBasicos = generarSugerenciaBasica();
		return atuendosBasicos.stream().map(atuendo -> combinarConSecundarios(atuendo))
				.flatMap(atuendos -> atuendos.stream()).filter(atuendo->atuendo.esAtuendoValido()).collect(Collectors.toSet());
	}

	public Set<Atuendo> combinarConSecundarios(Atuendo atuendoBasico) {
		Set<Prenda> prendasSecundarias = atuendoBasico.prendasPermitidas(superiores);
		prendasSecundarias.addAll(accesorios);
		Set<List<Prenda>> combinacionesSecundarias = combinacionesPosibles(prendasSecundarias);
		return combinacionesSecundarias.stream()
				.map(prendas -> crearAtuendoConPrendas(atuendoBasico,prendas)).collect(Collectors.toSet());
	}
	
	private Set<List<Prenda>> combinacionesPosibles(Set<Prenda> prendasSecundarias) {
		return Sets.powerSet(prendasSecundarias).stream().map(ps -> Collections2.permutations(ps)).
				flatMap(list->list.stream()).collect(Collectors.toSet());
	}

	private Atuendo crearAtuendoConPrendas(Atuendo atuendoBasico, List<Prenda> prendas) {
		Atuendo atuendo = new Atuendo(atuendoBasico.getSuperior(), atuendoBasico.getInferior(), atuendoBasico.getCalzado());
		List<Prenda> abrigosOrd = prendas.stream().filter(prenda -> prenda.esDeCategoria(Categoria.PARTE_SUPERIOR)).collect(Collectors.toList());
		List<Prenda> accesoriosOrd = prendas.stream().filter(prenda -> prenda.esDeCategoria(Categoria.ACCESORIO)).collect(Collectors.toList());
		atuendo.ponerAbrigos(abrigosOrd);
		atuendo.ponerAccesorios(accesoriosOrd);
		return atuendo;
	}

	public SugerenciasClima generarSugerenciasSegunClima(String ubicacion){
		SugerenciasClima sugerenciasClima = new SugerenciasClima(10);
		generarSugerenciasPosibles().stream()
				.forEach(atuendo -> sugerenciasClima.agregarAtuendoSegunClima(atuendo,ServicioClimatico.obtenerClima(ubicacion)));
		return sugerenciasClima;
	}

	public int cantidadDePrendas() { //TODO testear
		return superiores.size() + inferiores.size() + calzados.size() + accesorios.size();
	}

	public void agregarPrendas(Set<Prenda> prendas) {
		prendas.stream().forEach(prenda -> agregarPrenda(prenda));
	}

	public void agregarPrenda(Prenda prenda) { //@TODO testear
		if (existePrenda(prenda)) throw new PrendaYaExisteException();
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

	public void agregarImagenA(Prenda prenda, String rutaImagen) throws IOException {
		if (!existePrenda(prenda)) {
			throw new NoExistePrendaEnGuardarropaException(); //TODO testear
		}
		Imagen imagenOriginal = new Imagen(rutaImagen);
		prenda.agregarImagen(imagenOriginal);
	}

	public boolean existePrenda(Prenda prenda) {
		return (superiores.contains(prenda) || inferiores.contains(prenda) || calzados.contains(prenda)
				|| accesorios.contains(prenda));
	}

	public Set<Prenda> getSuperiores() {
		return superiores;
	}
}
