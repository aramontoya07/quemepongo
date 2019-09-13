package usuario;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import atuendo.SugerenciasClima;
import prenda.Categoria;

import com.google.common.collect.Sets;

import atuendo.Atuendo;
import clima.ServicioClimatico;
import db.EntidadPersistente;
import excepciones.GuardarropaException;
import excepciones.PrendaException;
import prenda.*;

@Entity
@Table(name = "Guardarropas")
public class Guardarropa extends EntidadPersistente{

	@OneToMany
	@JoinColumn(name = "id_Guardarropa")
	public Set<Prenda> usadas = new HashSet<>();
	@OneToMany
	@JoinColumn(name = "id_Guardarropa")
	public Set<Prenda> disponibles = new HashSet<>();

	public Guardarropa(){}

	public boolean prendaDisponible(Prenda prenda) {
		return disponibles.contains(prenda);
	}

	public boolean prendaOcupada(Prenda prenda) {
		return usadas.contains(prenda);
	}

	public void usarPrenda(Prenda prenda) {
		if(!prendaDisponible(prenda) || prendaOcupada(prenda)){
			throw new GuardarropaException("No se puede usar la prenda porque no esta disponible en el guardarropa");
		}
		quitarDeDisponibles(prenda);
		agregarAUsadas(prenda);
	}

	public void liberarPrenda(Prenda prenda) throws PrendaException {
		if (prendaDisponible(prenda) || !prendaOcupada(prenda)){
			throw new GuardarropaException("No se puede liberar la prenda porque no esta siendo usada");
		}
		agregarADisponibles(prenda);
		quitarDeUsadas(prenda);
	}

	public void agregarPrenda(Prenda prenda) throws PrendaException {
		if (prendaDisponible(prenda) || prendaOcupada(prenda)){
			throw new GuardarropaException("No se puede agregar la prenda porque ya existe en el guardarropa");
		}
		agregarADisponibles(prenda);
	}

	private void quitarDeDisponibles(Prenda prenda) {
		disponibles.remove(prenda);
	}

	public void quitarDeUsadas(Prenda prenda) {
		usadas.remove(prenda);
	}

	public void agregarAUsadas(Prenda prenda) {
		usadas.add(prenda);
	}

	public void agregarADisponibles(Prenda prenda){
		disponibles.add(prenda);
	}

	public Set<Prenda> getPrendasUsadas(){
		return usadas;
	}

	public Set<Prenda> getPrendasDisponibles(){
		return disponibles;
	}

	@SuppressWarnings("unchecked")
	public Set<Atuendo> generarSugerenciaBasica(){
		return Sets
				.cartesianProduct(prendasPrimarias(
					getPrendasDeParte(Categoria.PARTE_SUPERIOR)), 
					getPrendasDeParte(Categoria.PARTE_INFERIOR), 
					getPrendasDeParte(Categoria.CALZADO))
				.stream().map((list) -> new Atuendo(list.get(0), list.get(1), list.get(2), this))
				.collect(Collectors.toSet());
	}

	public Set<Prenda> getPrendasDeParte(Categoria categoria) {
		return disponibles.stream().filter(prenda->prenda.esDeCategoria(categoria))
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
		prendasSecundarias.addAll(getPrendasDeParte(Categoria.PARTE_SUPERIOR));
		prendasSecundarias.addAll(getPrendasDeParte(Categoria.ACCESORIO));
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
				if(listaSecundaria.stream().noneMatch(atuendoSec -> atuendoSec.esIgualA(atuendo))){
					listaSecundaria.add(atuendo);
				}
			}

			public void combinarPrendas(Atuendo atuendo,Set<Prenda> prendasSecundarias){

				combinaciones.add(atuendo);

				Atuendo atuendoActual = atuendo.clonar();

				Set<Prenda> prendasActuales = new HashSet<Prenda>(prendasSecundarias);

				for(Prenda prendaActual : prendasSecundarias){
					if(atuendo.aceptaSuperponer(prendaActual)){
						atuendoActual.agregarAbrigo(prendaActual);
						prendasActuales.remove(prendaActual);

						combinarPrendas(atuendoActual,prendasActuales);

						prendasActuales = new HashSet<Prenda>(prendasSecundarias);
						atuendoActual = atuendo.clonar();
					}
				}
			}
		}

		CombinadorPrendas combinador = new CombinadorPrendas();
		combinador.combinarPrendas(atuendo,prendasSecundarias);

		return combinador.getCombinaciones();
	}

	public void agregarImagenA(Prenda prenda, String rutaImagen) throws IOException {
		if (!prendaDisponible(prenda)) {
			throw new GuardarropaException("No se puede agregar imagen a la prenda ya que no esta disponible en el guardarropa");
		}
		Imagen imagenOriginal = new Imagen(rutaImagen);
		prenda.agregarImagen(imagenOriginal);
	}

	public SugerenciasClima generarSugerenciasSegunClima(String ubicacion){
		SugerenciasClima sugerenciasClima = new SugerenciasClima();
		Set<Atuendo> posibles = generarSugerenciasPosibles();
		posibles.stream().forEach(atuendo -> sugerenciasClima.agregarAtuendoSegunClima(atuendo,ServicioClimatico.obtenerClima(ubicacion)));
		return sugerenciasClima;
	}

	public int cantidadDePrendas() {
		return disponibles.size();
	}

	public void agregarPrendas(Set<Prenda> prendas) {
		prendas.stream().forEach(prenda -> agregarADisponibles(prenda));
	}
}