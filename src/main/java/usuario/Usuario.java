package usuario;

import java.util.*;
import java.util.stream.Collectors;

import alertas.Interesado;
import alertas.RepoUsuarios;
import alertas.TipoAlerta;
import atuendo.Atuendo;
import atuendo.SugerenciasClima;
import clima.ServicioClimatico;
import decisiones.Decision;
import decisiones.DecisionAceptar;
import decisiones.DecisionRechazar;
import eventos.AsistenciaEvento;
import eventos.Calendario;
import eventos.Evento;
import excepciones.AgregarPrendaException;
import excepciones.GuardarropasNoEncontradoException;
import excepciones.GuardarropasYaAgregadoException;
import excepciones.PrendaFaultException;
import prenda.ParteAbrigada;
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
	private Set<Guardarropa> guardarropas = new HashSet<>();
	private String mail;
	private List<Interesado> interesados = new ArrayList<>();
	private PreferenciasDeAbrigo preferenciasDeAbrigo;


	public Usuario() {
		this.subscripcion = new SubscripcionGratuita();
		RepoUsuarios.getInstance().agregarUsuario(this);
		preferenciasDeAbrigo = new PreferenciasDeAbrigo();
	}

	public Set<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public String getMail() {
		return mail;
	}

	public void actualizarSubscripcion() {
		subscripcion = new SubscripcionPremium();
	}

	public void cancelarPremium() {
		subscripcion = new SubscripcionGratuita();
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		if(guardarropas.contains(guardarropa)) throw new GuardarropasYaAgregadoException();
		guardarropas.add(guardarropa);
	}

	public void agregarPrendas(Guardarropa guardarropa, Set<Prenda> prendas) {
		if(!guardarropas.contains(guardarropa)) throw new GuardarropasNoEncontradoException();
		prendas.forEach(prenda -> agregarPrenda(guardarropa, prenda));
	}

	private void agregarPrenda(Guardarropa guardarropa, Prenda prenda) {
		if(!subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) throw new AgregarPrendaException();
		guardarropa.agregarADisponibles(prenda);
	}

	public void asistirAEvento(Evento evento){
		calendarioEventos.agregarEvento(evento,this);
	}

	public Set<Atuendo> pedirSugerencia(){
		return guardarropas.stream().map(Guardarropa::generarSugerenciasPosibles)
				.flatMap(Collection::stream).collect(Collectors.toSet());
	}

	public Set<SugerenciasClima> pedirSugerenciaSegunClima(String ubicacion) {
		return guardarropas.stream().map(unGuardarropa -> unGuardarropa.generarSugerenciasSegunClima(ubicacion))
				.map(sugerencias -> sugerencias.ajustarAGustos(preferenciasDeAbrigo, ServicioClimatico.obtenerClima(ubicacion).getTemperatura())).collect(Collectors.toSet());
	}
	
	public Set<SugerenciasClima> pedirSugerenciaParaEvento(Evento evento) {
		return calendarioEventos.pedirSugerenciasParaEvento(evento);
	}

	public void aceptarAtuendo(Atuendo atuendo) throws PrendaFaultException {
		chequearAtuendoDisponible(atuendo);
		aceptados.add(atuendo);
		atuendo.marcarPrendasComoUsadas();
		ultimaDecision = new DecisionAceptar();
	}

	private void chequearAtuendoDisponible(Atuendo atuendo)throws PrendaFaultException {
		if(!atuendo.estaDisponible()){
			throw new PrendaFaultException("Algunas prendas del atuendo elegido ya no se hallan disponibles");
		}
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
		assert atuendo != null;
		atuendo.liberarPrendasUsadas();
	}

	public void removerRechazado() {
		rechazados.poll();
	}

	public void puntuarParte(AdaptacionPuntuada nuevoPuntaje, ParteAbrigada parte) {
			AdaptacionPuntuada puntajeAbrigo = preferenciasDeAbrigo.getPuntaje(parte);
			puntajeAbrigo.setearElMejor(nuevoPuntaje);
	}



	public void actuarAnte(TipoAlerta alerta) {
		interesados.forEach(interesado -> alerta.notificarA(interesado, this));
	}

	public PreferenciasDeAbrigo getPreferenciasDeAbrigo() {
		return preferenciasDeAbrigo;
	}

	public void notificarAlerta(Interesado interesado, TipoAlerta alerta) {
		alerta.notificarA(interesado, this);
	}
	
	public void notificarSugerenciasListas(AsistenciaEvento asistencia) {
		interesados.forEach(interesado -> interesado.notificarA(this, asistencia));
	}


	public void removerPuntuado(PreferenciasDeAbrigo preferenciaAntigua) {
		preferenciasDeAbrigo = preferenciaAntigua;
	}

	public Calendario getCalendarioEventos() {
		return calendarioEventos;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}