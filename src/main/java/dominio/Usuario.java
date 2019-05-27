package dominio;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import excepciones.AgregarPrendaException;

public class Usuario {
	public Decision ultimaDecision;
	public Queue<Atuendo> aceptados;
	public Queue<Atuendo> rechazados;
	public List<Guardarropas> guardarropas;
	public TipoSubscripcion subscripcion;
	
	public void agregarPrenda(Guardarropas guardarropa, Prenda prenda){
		if(subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) {
			guardarropa.agregarPrenda(prenda);
		}
		else {
			throw new AgregarPrendaException();
		}
	}

	public Usuario(List<Guardarropas> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public List<Atuendo> pedirSugerencia() {
		return guardarropas.stream() 
				.map(unGuardarropa -> unGuardarropa.generarSugerencia()) 
				.flatMap(atuendos -> atuendos.stream()) 
				.collect(Collectors.toList());
	}
	
	public void aceptarAtuendo(Atuendo atuendo) {
		aceptados.add(atuendo);
		ultimaDecision = new DecisionAceptar();
	}

	public void rechazarAtuendo(Atuendo atuendo) {
		rechazados.add(atuendo);
		ultimaDecision = new DecisionRechazar();
	}

	public void deshacerDecision() {
		ultimaDecision.deshacerEn(this);
	}

	public void removerAceptado() {
		aceptados.poll();
	}

	public void removerRechazado() {
		rechazados.poll();
	}
}