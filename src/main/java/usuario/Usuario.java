package usuario;

import java.util.*;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import atuendo.Sugerencias;
import clima.ServicioClimatico;
import decisiones.Decision;
import decisiones.DecisionAceptar;
import decisiones.DecisionRechazar;
import eventos.Evento;
import excepciones.AgregarPrendaException;
import excepciones.GuardarropasNoEncontradoException;
import excepciones.GuardarropasYaAgregadoException;
import prenda.Prenda;
import subscripciones.SubscripcionGratuita;
import subscripciones.SubscripcionPremium;
import subscripciones.TipoSubscripcion;

public class Usuario {
	private Decision ultimaDecision;
	private TipoSubscripcion subscripcion;
	private Queue<Atuendo> aceptados = new LinkedList<>();
	private Queue<Atuendo> rechazados = new LinkedList<>();
	private Set<Guardarropas> guardarropas = new HashSet<>();
	private Set<Evento> eventos = new HashSet<>();

	public Usuario() {
		this.subscripcion = new SubscripcionGratuita();
	}

	public Set<Guardarropas> getGuardarropas() {
		return guardarropas;
	}

	public void actualizarSubscripcion() {
		subscripcion = new SubscripcionPremium();
	}

	public void cancelarPremium() {
		subscripcion = new SubscripcionGratuita();
	}

	public void agregarGuardarropa(Guardarropas guardarropa) {
		if(guardarropas.contains(guardarropa)) throw new GuardarropasYaAgregadoException();
		guardarropas.add(guardarropa);
	}

	public void agregarPrendas(Guardarropas guardarropa, Set<Prenda> prendas) {
		if(!guardarropas.contains(guardarropa)) throw new GuardarropasNoEncontradoException();
		prendas.forEach(prenda -> agregarPrenda(guardarropa, prenda));
	}

	private void agregarPrenda(Guardarropas guardarropa, Prenda prenda) {
		if(!subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) throw new AgregarPrendaException();
		guardarropa.agregarPrenda(prenda);
	}

	public void agregarEvento(Evento evento){
		eventos.add(evento);
		Timer tarea = new Timer();
		tarea.schedule(evento, evento.dateEvento().getTime());
	}

	public Set<Atuendo> pedirSugerencia(){
		return guardarropas.stream().map(Guardarropas::generarSugerenciasPosibles)
				.flatMap(Collection::stream).collect(Collectors.toSet());
	}

	public Set<Sugerencias> pedirSugerenciaSegunClima(ServicioClimatico provedor, String ubicacion) {
		return guardarropas.stream().map(unGuardarropa -> unGuardarropa.generarSugerenciasSegunClima(provedor, ubicacion))
				.collect(Collectors.toSet());
	}

	public Set<Sugerencias> pedirSugerenciaParaEvento(Evento evento) {
		return evento.pedirSugerencias();
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