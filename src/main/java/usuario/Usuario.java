package usuario;

import java.util.*;
import java.util.stream.Collectors;

import alertas.Interesado;
import alertas.TipoAlerta;
import atuendo.Atuendo;
import atuendo.SugerenciasClima;
import decisiones.Decision;
import decisiones.DecisionAceptar;
import decisiones.DecisionRechazar;
import eventos.Calendario;
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
	private Calendario calendarioEventos = new Calendario();
	private Queue<Atuendo> aceptados = new LinkedList<>();
	private Queue<Atuendo> rechazados = new LinkedList<>();
	private Set<Guardarropas> guardarropas = new HashSet<>();
	private String mail;
	private List<Interesado> interesados = new ArrayList<>();


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

	public void agregarPrenda(Guardarropas guardarropa, Prenda prenda) {
		if(!subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) throw new AgregarPrendaException();
		guardarropa.agregarPrenda(prenda);
	}

	public void asistirAEvento(Evento evento){
		calendarioEventos.agregarEvento(evento,this);
	}

	public Set<Atuendo> pedirSugerencia(){
		return guardarropas.stream().map(Guardarropas::generarSugerenciasPosibles)
				.flatMap(Collection::stream).collect(Collectors.toSet());
	}

	public Set<SugerenciasClima> pedirSugerenciaSegunClima(String ubicacion) {
		return guardarropas.stream().map(unGuardarropa -> unGuardarropa.generarSugerenciasSegunClima(ubicacion))
				.collect(Collectors.toSet());
	}
	
	public Set<SugerenciasClima> pedirSugerenciaParaEvento(Evento evento) {
		return new HashSet<SugerenciasClima>();
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
		Atuendo atuendo = aceptados.poll();
	}

	public void removerRechazado() {
		rechazados.poll();
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void actuarAnte(TipoAlerta alerta) {
		this.interesados.forEach(interesado -> alerta.notificarA(interesado, this));
	}

	public void notificarAlerta(Interesado interesado, TipoAlerta alerta)
	{
		alerta.notificarA(interesado, this);
	}


}