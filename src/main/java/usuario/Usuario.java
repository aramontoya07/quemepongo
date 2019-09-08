package usuario;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import alertas.Alerta;
import notificaciones.Informante;
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
import excepciones.AtuendoException;
import excepciones.GuardarropaException;
import excepciones.PrendaException;
import prenda.ParteAbrigada;
import prenda.Prenda;
import subscripciones.SubscripcionGratuita;
import subscripciones.SubscripcionPremium;
import subscripciones.TipoSubscripcion;

@Entity
@Table(name = "Usuarios")
public class Usuario extends EntidadPersistente{
	
	//@OneToMany
	@Transient
	private Queue<Atuendo> aceptados = new LinkedList<>();
	//@OneToMany
	@Transient
	private Queue<Atuendo> rechazados = new LinkedList<>();
	//@ManyToMany
	@Transient
	private Set<Guardarropa> guardarropas = new HashSet<>();
	//@OneToMany
	@Transient
	private List<Informante> informantes = new ArrayList<>();
	//@OneToOne
	@Transient
	private Decision ultimaDecision;
	@ManyToOne
	private TipoSubscripcion subscripcion;
	//@OneToOne
	@Transient
	private Calendario calendarioEventos = new Calendario();
	//@OneToOne
	@Transient
	private PreferenciasDeAbrigo preferenciasDeAbrigo;
	private String mail;
	//@Transient
	private boolean notificado = false;

	
	public List<Informante> getInformantes() {
		return informantes;
	}

	public void setInformantes(List<Informante> informantes) {
		this.informantes = informantes;
	}

	public Decision getUltimaDecision() {
		return ultimaDecision;
	}

	public void setUltimaDecision(Decision ultimaDecision) {
		this.ultimaDecision = ultimaDecision;
	}

	public TipoSubscripcion getSubscripcion() {
		return subscripcion;
	}

	public void setSubscripcion(TipoSubscripcion subscripcion) {
		this.subscripcion = subscripcion;
	}

	public void setAceptados(Queue<Atuendo> aceptados) {
		this.aceptados = aceptados;
	}

	public void setRechazados(Queue<Atuendo> rechazados) {
		this.rechazados = rechazados;
	}

	public void setGuardarropas(Set<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public void setCalendarioEventos(Calendario calendarioEventos) {
		this.calendarioEventos = calendarioEventos;
	}

	public void setPreferenciasDeAbrigo(PreferenciasDeAbrigo preferenciasDeAbrigo) {
		this.preferenciasDeAbrigo = preferenciasDeAbrigo;
	}

	public void setNotificado(boolean notificado) {
		this.notificado = notificado;
	}
	public void marcarNotificado(){
		notificado = true;
	}

	public boolean getNotificado(){
		return notificado;
	}

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

	public void agregarInformante(Informante informante) {
		informantes.add(informante);
	}

	public void quitarInformante(Informante informante){
		informantes.remove(informante);
	}

	public void agregarGuardarropa(Guardarropa guardarropa) {
		if(guardarropas.contains(guardarropa)) throw new GuardarropaException("El guardarropa que se intento agregar ya existe");
		guardarropas.add(guardarropa);
	}

	//fixme y esto? Maxi dice, que fede lo obligo a codearlo, Fede argumenta que se esta adelantando a un requerimiento.
	public void resetearGustos(){
		preferenciasDeAbrigo = new PreferenciasDeAbrigo();
	}

	public void agregarPrendas(Guardarropa guardarropa, Set<Prenda> prendas) {
		if(!guardarropas.contains(guardarropa)) throw new GuardarropaException("No se puede agregar la prenda ya que el guardarropa no existe");
		prendas.forEach(prenda -> agregarPrenda(guardarropa, prenda));
	}

	public void agregarPrenda(Guardarropa guardarropa, Prenda prenda) {
		if(!subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) throw new GuardarropaException("El guardarropa posee demasiadas prendas para ser agregado por un usuario con subscripcion gratuita");
		guardarropa.agregarADisponibles(prenda);
	}

	public void asistirAEvento(Evento evento){
		calendarioEventos.agregarEvento(evento,this);
	}

	public Set<Atuendo> pedirSugerencia(){ //se usa principalmente para tests
		return guardarropas.stream().map(Guardarropa::generarSugerenciasPosibles)
				.flatMap(Collection::stream).collect(Collectors.toSet());
	}

	public Set<SugerenciasClima> pedirSugerenciaSegunClima(String ubicacion){
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

	public void puntuarParteDeAtuendoEn(Atuendo atuendo, Integer puntaje, ParteAbrigada parte) throws AtuendoException{
			if(!aceptados.contains(atuendo)) throw new AtuendoException("No se puede puntuar un atuendo sin antes haberlo aceptado");
			AdaptacionPuntuada nuevoPuntaje = new AdaptacionPuntuada(atuendo.abrigoEn(parte), atuendo.getTemperaturaDeUso(), puntaje);
			AdaptacionPuntuada puntajeAbrigo = preferenciasDeAbrigo.getPuntaje(parte);
			puntajeAbrigo.setearElMejor(nuevoPuntaje);
	}

	public void actuarAnte(Alerta alerta){
		informantes.forEach(informante -> alerta.getTipo().notificarA(informante, this));
	}

	public PreferenciasDeAbrigo getPreferenciasDeAbrigo() {
		return preferenciasDeAbrigo;
	}

	public void notificarAlerta(Informante informante, TipoDeAlerta alerta) {
		alerta.notificarA(informante, this);
	}

	public void notificarSugerenciasListas(AsistenciaEvento asistencia) {
		informantes.forEach(informante -> informante.notificarA(this, asistencia));
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

	public boolean leInteresaLaUbicacion(String ubicacion){
		return calendarioEventos.hayEventosCercanosEn(ubicacion);
	}

	public Set<AsistenciaEvento> obtenerEventosEntre(LocalDateTime fechaMinima, LocalDateTime fechaMaxima){
		return calendarioEventos.obtenerEventosEntre(fechaMinima, fechaMaxima);
	}
	public void quitarEvento(Evento evento){
		calendarioEventos.quitarEvento(evento);
	}
	public int getPuntajeEn(ParteAbrigada parte) {
		return preferenciasDeAbrigo.getPuntaje(parte).getPuntaje();
	}

	public Double getAbrigoPreferidoEn(ParteAbrigada parte) {
		return preferenciasDeAbrigo.getPuntaje(parte).getNivelDeAdaptacion();
	}
}
