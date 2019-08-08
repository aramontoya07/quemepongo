package usuario;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.SugerenciasClima;

import com.google.common.collect.Sets;

import atuendo.Atuendo;
import clima.ServicioClimatico;
import excepciones.GuardarropaException;
import excepciones.PrendaException;
import prenda.*;

public class Guardarropa {

	public Set<Prenda> superiores = new HashSet<>();
	public Set<Prenda> inferiores = new HashSet<>();
	public Set<Prenda> calzados = new HashSet<>();
	public Set<Prenda> accesorios = new HashSet<>();
	private int margenDePrendasAproximadas = 10;
	
	public Set<Prenda> prendasUsadas = new HashSet<>();
	
	public Guardarropa() { }

	public void usarPrenda(Prenda prenda) {
		if(!existePrenda(prenda)){
			throw new GuardarropaException("No se puede usar la prenda porque no está disponible en el guardarropa");
		}
		quitarDeDisponibles(prenda);
		agregarAUsadas(prenda);
	}

	public void liberarPrenda(Prenda prenda) throws PrendaException {
		agregarADisponibles(prenda);
		quitarDeUsadas(prenda);
	}

	public Set<Prenda> getPrendasUsadas(){
		return prendasUsadas;
	}

	public Set<Prenda> prendasDisponibles(){
		Set<Prenda> prendas = new HashSet <>();
		prendas.addAll(superiores);
		prendas.addAll(inferiores);
		prendas.addAll(calzados);
		prendas.addAll(accesorios);
		return prendas;
	}

	public void quitarDeUsadas(Prenda prenda){
		prendasUsadas.remove(prenda);
	}

	public void agregarAUsadas(Prenda prenda){
		prendasUsadas.add(prenda);
	}

	@SuppressWarnings("unchecked")
	public Set<Atuendo> generarSugerenciaBasica(){
		return Sets
				.cartesianProduct(prendasPrimarias(superiores), inferiores, calzados)
				.stream().map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), this))
				.collect(Collectors.toSet());
	}

	public Set<Prenda> prendasPrimarias(Set<Prenda> prendas){
		return prendas.stream().filter(prenda -> prenda.esPrimaria()).collect(Collectors.toSet());
	}

	public Set<Atuendo> generarSugerenciasPosibles(){
		Set<Atuendo> atuendosBasicos = generarSugerenciaBasica();
		return atuendosBasicos.stream().map(atuendo -> combinarConSecundarios(atuendo)).flatMap(atuendo -> atuendo.stream()).collect(Collectors.toSet());
	}

	public Set<Atuendo> combinarConSecundarios(Atuendo atuendoBasico) {
		Set<Prenda> prendasSecundarias = new HashSet<Prenda>();
		prendasSecundarias.addAll(superiores);
		prendasSecundarias.addAll(accesorios);
		return combinacionesPosibles(atuendoBasico,prendasSecundarias);
	}

	private Set<Atuendo> combinacionesPosibles(Atuendo atuendo, Set<Prenda> prendasSecundarias){

		class CombinadorPrendas{
			Set<Atuendo> combinaciones = new HashSet<Atuendo>();

			public Set<Atuendo> getCombinaciones() {
				return eliminarDuplicados(combinaciones);
			}

			private Set<Atuendo> eliminarDuplicados(Set<Atuendo> combinaciones){
				Set<Atuendo> listaSecundaria = new HashSet<>();
				combinaciones.forEach(atuendo -> agregarSiNoEsta(listaSecundaria,atuendo));
				return listaSecundaria;
			}

			private void agregarSiNoEsta(Set<Atuendo> listaSecundaria, Atuendo atuendo) {
				if(listaSecundaria.stream().anyMatch(atuendoSec -> atuendoSec.esIgualA(atuendo))){
					return;
				}else{
					listaSecundaria.add(atuendo);
				}
			}

			public void combinarPrendas(Atuendo atuendo,Set<Prenda> prendasSecundarias){

				combinaciones.add(atuendo);

				Atuendo atuendoActual = atuendo.clonar();

				Set<Prenda> prendasActuales = new HashSet(prendasSecundarias);

				for(Prenda prendaActual : prendasSecundarias){
					if(atuendo.aceptaSuperponer(prendaActual)){
						atuendoActual.agregarAbrigo(prendaActual);
						prendasActuales.remove(prendaActual);

						combinarPrendas(atuendoActual,prendasActuales);

						prendasActuales = new HashSet(prendasSecundarias);
						atuendoActual = atuendo.clonar();
					}
				}
			}
		}

		CombinadorPrendas combinador = new CombinadorPrendas();
		combinador.combinarPrendas(atuendo,prendasSecundarias);

		return combinador.getCombinaciones();
	}

	public SugerenciasClima generarSugerenciasSegunClima(String ubicacion){
		SugerenciasClima sugerenciasClima = new SugerenciasClima(margenDePrendasAproximadas);
		Set<Atuendo> posibles = generarSugerenciasPosibles();
		posibles.stream().forEach(atuendo -> sugerenciasClima.agregarAtuendoSegunClima(atuendo,ServicioClimatico.obtenerClima(ubicacion)));
		return sugerenciasClima;
	}

	public void setMargenDePrendasAproximadas(int margenDePrendasAproximadas) {
		this.margenDePrendasAproximadas = margenDePrendasAproximadas;
	}

	public int cantidadDePrendas() {
		return superiores.size() + inferiores.size() + calzados.size() + accesorios.size();
	}

	public void agregarPrendas(Set<Prenda> prendas) {
		prendas.stream().forEach(prenda -> agregarADisponibles(prenda));
	}

	public void agregarADisponibles(Prenda prenda) {
		if (existePrenda(prenda)) throw new GuardarropaException("No se puede agregar la prenda al guardarropa ya que esta ya se encuentra disponible en el");
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

	public void quitarDeDisponibles(Prenda prenda) {
		if (!existePrenda(prenda)) throw new PrendaException("No se puede remover la prenda porque no existe en el guardarropa");
		switch (prenda.getCategoria()){
			case PARTE_SUPERIOR:
				superiores.remove(prenda);
				break;
			case PARTE_INFERIOR:
				inferiores.remove(prenda);
				break;
			case CALZADO:
				calzados.remove(prenda);
				break;
			case ACCESORIO:
				accesorios.remove(prenda);
				break;
		}
	}

	public void agregarImagenA(Prenda prenda, String rutaImagen) throws IOException {
		if (!existePrenda(prenda)) {
			throw new GuardarropaException("No se puede agregar imagen a la prenda ya que no esta disponible en el guardarropa");
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
