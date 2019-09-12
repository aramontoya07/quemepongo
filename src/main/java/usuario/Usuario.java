package usuario;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;
import alertas.Alerta;
import db.EntidadPersistente;
import notificaciones.Informante;
import alertas.RepoUsuarios;
import alertas.TipoDeAlerta;
import atuendo.Atuendo;
import atuendo.EstadoAtuendo;
import atuendo.SugerenciasClima;
import atuendo.UsoAtuendo;
import clima.ServicioClimatico;
import decisiones.Decision;
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
public class Usuario extends EntidadPersistente {
	
	@OneToMany
	@JoinColumn(name = "Id_usuario")
	private List<UsoAtuendo> atuendosUsados = new ArrayList<>();

	@ManyToMany
	private Set<Guardarropa> guardarropas = new HashSet<>();

	@ElementCollection(targetClass = Informante.class)
	@Enumerated(EnumType.STRING)
	private List<Informante> informantes = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private Decision ultimaDecision;

	@ManyToOne
	private TipoSubscripcion subscripcion;

	@OneToOne
	private Calendario calendarioEventos = new Calendario();

	@OneToOne
	private PreferenciasDeAbrigo preferenciasDeAbrigo;

	private String mail;	

	public Usuario() {
		this.subscripcion = new SubscripcionGratuita();
		RepoUsuarios.getInstance().agregarUsuario(this);
		preferenciasDeAbrigo = new PreferenciasDeAbrigo();
	}

	//GUARDARROPAS

	public void agregarGuardarropa(Guardarropa guardarropa) {
		if(guardarropas.contains(guardarropa)) throw new GuardarropaException("El guardarropa que se intento agregar ya existe");
		guardarropas.add(guardarropa);
	}

	public void agregarPrendas(Guardarropa guardarropa, Set<Prenda> prendas) {
		if(!guardarropas.contains(guardarropa)) throw new GuardarropaException("No se puede agregar la prenda ya que el guardarropa no existe");
		prendas.forEach(prenda -> agregarPrenda(guardarropa, prenda));
	}

	public void agregarPrenda(Guardarropa guardarropa, Prenda prenda) {
		if(!subscripcion.puedoAgregar(guardarropa.cantidadDePrendas())) throw new GuardarropaException("El guardarropa posee demasiadas prendas para ser agregado por un usuario con subscripcion gratuita");
		guardarropa.agregarADisponibles(prenda);
	}


	//SUSCRIPCIONES

	public void actualizarSubscripcionAPremium() {
		subscripcion = new SubscripcionPremium();
	}

	public void cancelarPremium() {
		subscripcion = new SubscripcionGratuita();
	}

	//SUGERENCIAS

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

	//EVENTOS

	public void asistirAEvento(Evento evento){
		calendarioEventos.agregarEvento(evento,this);
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

	//EVALUAR SUGERENCIAS

	public void aceptarAtuendo(Atuendo atuendo) throws PrendaException {
		chequearAtuendoDisponible(atuendo);
		UsoAtuendo uso = new UsoAtuendo(atuendo,EstadoAtuendo.ACEPTADO);
		atuendosUsados.add(uso);
		atuendo.marcarPrendasComoUsadas();
		ultimaDecision  = Decision.ACEPTAR;
	}

	public void rechazarAtuendo(Atuendo atuendo) {
		UsoAtuendo uso = new UsoAtuendo(atuendo,EstadoAtuendo.RECHAZADO);
		atuendosUsados.add(uso);
		ultimaDecision = Decision.RECHAZAR;
	}

	private void chequearAtuendoDisponible(Atuendo atuendo)throws PrendaException {
		if(!atuendo.estaDisponible()){
			throw new PrendaException("Algunas prendas del atuendo elegido ya no se hallan disponibles");
		}
	}

	public Set<Atuendo> getRechazados(){
		return atuendosUsados.stream().filter( usoAtuendo -> usoAtuendo.getEstado().equals(EstadoAtuendo.RECHAZADO)).
			map( usoAtuendo -> usoAtuendo.getAtuendo()).
				collect(Collectors.toSet());
	}

	public Set <Atuendo> getAceptados() {
		return atuendosUsados.stream().filter( usoAtuendo -> usoAtuendo.getEstado().equals(EstadoAtuendo.ACEPTADO)).
		map( usoAtuendo -> usoAtuendo.getAtuendo()).
			collect(Collectors.toSet());
	}

	//DECISIONES

	public void deshacerDecision() {
		ultimaDecision.deshacerEn(this);
	}

	public void removerAceptado() {
		List<UsoAtuendo> usosDeAceptados = atuendosUsados.stream().filter(uso -> uso.getEstado().
			equals(EstadoAtuendo.ACEPTADO)).collect(Collectors.toList());
		
		UsoAtuendo usoARemover = usosDeAceptados.stream().sorted(Comparator.comparing(uso -> uso.getFechaDeUso())).collect(Collectors.toList()).get(0);
		Atuendo atuendoARemover = usoARemover.getAtuendo();

		atuendosUsados.remove(usoARemover);
		
		if(atuendoARemover != null){
			atuendoARemover.liberarPrendasUsadas();
		}
	}

	public void removerRechazado() {
		List<UsoAtuendo> usosDeAceptados = atuendosUsados.stream().filter(uso -> uso.getEstado().
		equals(EstadoAtuendo.RECHAZADO)).collect(Collectors.toList());
	
		UsoAtuendo usoARemover = usosDeAceptados.stream().sorted(Comparator.comparing(uso -> uso.getFechaDeUso())).collect(Collectors.toList()).get(0);
		atuendosUsados.remove(usoARemover);
	}

	//NOTIFIACIONES

	public void agregarInformante(Informante informante) {
		informantes.add(informante);
	}

	public void quitarInformante(Informante informante){
		informantes.remove(informante);
	}

	public void notificarAlerta(Informante informante, TipoDeAlerta alerta) {
		alerta.notificarA(informante, this);
	}

	public void notificarSugerenciasListas(AsistenciaEvento asistencia) {
		informantes.forEach(informante -> informante.notificarA(this, asistencia));
	}

	public void actuarAnte(Alerta alerta){
		informantes.forEach(informante -> alerta.getTipo().notificarA(informante, this));
	}

	//PUNTUAR ATUENDOS

	public void puntuarParteDeAtuendoEn(UsoAtuendo uso, Integer puntaje, ParteAbrigada parte) throws AtuendoException{
		if(!getAceptados().contains(uso.getAtuendo())) throw new AtuendoException("No se puede puntuar un atuendo sin antes haberlo aceptado");
		AdaptacionPuntuada nuevoPuntaje = new AdaptacionPuntuada(uso.getAtuendo().abrigoEn(parte), uso.getTemperaturaDeUso(), puntaje);
		AdaptacionPuntuada puntajeAbrigo = preferenciasDeAbrigo.getPuntaje(parte);
		puntajeAbrigo.setearElMejor(nuevoPuntaje);
	}

	public void resetearGustos(){
		preferenciasDeAbrigo = new PreferenciasDeAbrigo();
	}

	public int getPuntajeEn(ParteAbrigada parte) {
		return preferenciasDeAbrigo.getPuntaje(parte).getPuntaje();
	}

	public Double getAbrigoPreferidoEn(ParteAbrigada parte) {
		return preferenciasDeAbrigo.getPuntaje(parte).getNivelDeAdaptacion();
	}


	/*------------------------------------GETTERS Y SETTERS-----------------------------------------*/


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

	public boolean getNotificado(){
		return notificado;
	}

	public Set<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public String getMail() {
		return mail;
	}

	public PreferenciasDeAbrigo getPreferenciasDeAbrigo() {
		return preferenciasDeAbrigo;
	}

	public Calendario getCalendarioEventos() {
		return calendarioEventos;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}



	
	/*----------------------------------------------PARA TEST--------------------------------------------*/

	private boolean notificado = false;
	
	public void marcarNotificado(){
		notificado = true;
	}

}
