package usuario;

import java.util.*;
import java.util.stream.Collectors;

import alertas.Interesado;
import alertas.RepoUsuarios;
import alertas.TipoDeAlerta;
import atuendo.Atuendo;
import atuendo.SugerenciasClima;
import clima.ServicioClimatico;
import decisiones.Decision;
import decisiones.DecisionAceptar;
import decisiones.DecisionRechazar;
import eventos.AsistenciaEvento;
import eventos.Calendario;
import eventos.Evento;
import excepciones.GuardarropaException;
import excepciones.PrendaException;
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

	public Queue <Atuendo> getRechazados() {
		return rechazados;
	}

	public Queue <Atuendo> getAceptados() {
		return aceptados;
	}

	public Set<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public String getMail() {
		return mail;
	}

	public void actualizarSubscripcionAPremium() {
		subscripcion = new SubscripcionPremium();
	}

	public void cancelarPremium() {
		subscripcion = new SubscripcionGratuita();
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		if(guardarropas.contains(guardarropa)) throw new GuardarropaException("El guardarropa que se intento agregar ya existe");
		guardarropas.add(guardarropa);
	}

	public void agregarPrendas(Guardarropa guardarropa, Set<Prenda> prendas) {
		if(!guardarropas.contains(guardarropa)) throw new GuardarropaException("No se puede agregar la prenda ya que el guardarropa no existe");
		prendas.forEach(prenda -> agregarPrenda(guardarropa, prenda));
	}

	private void agregarPrenda(Guardarropa guardarropa, Prenda prenda) {
		if(!subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) throw new GuardarropaException("El guardarropa posee demasiadas prendas para ser agregado por un usuario con subscripcion gratuita");
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

	public void aceptarAtuendo(Atuendo atuendo) throws PrendaException {
		chequearAtuendoDisponible(atuendo);
		aceptados.add(atuendo);
		atuendo.marcarPrendasComoUsadas();
		ultimaDecision = new DecisionAceptar();
	}

	private void chequearAtuendoDisponible(Atuendo atuendo)throws PrendaException {
		if(!atuendo.estaDisponible()){
			throw new PrendaException("Algunas prendas del atuendo elegido ya no se hallan disponibles");
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
		if(atuendo != null){
			atuendo.liberarPrendasUsadas();
		}
	}

	public void removerRechazado() {
		rechazados.poll();
	}

	public void puntuarParte(AdaptacionPuntuada nuevoPuntaje, ParteAbrigada parte) {
			AdaptacionPuntuada puntajeAbrigo = preferenciasDeAbrigo.getPuntaje(parte);
			puntajeAbrigo.setearElMejor(nuevoPuntaje);
	}

	public void actuarAnte(TipoDeAlerta alerta) {
		interesados.forEach(interesado -> alerta.notificarA(interesado, this));
	}

	public PreferenciasDeAbrigo getPreferenciasDeAbrigo() {
		return preferenciasDeAbrigo;
	}

	public void notificarAlerta(Interesado interesado, TipoDeAlerta alerta) {
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