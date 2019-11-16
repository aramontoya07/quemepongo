package applicationModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import eventos.AsistenciaEvento;
import eventos.Evento;
import eventos.Frecuencia;
import excepciones.EventoException;
import org.uqbar.commons.model.annotations.Observable;
import usuario.Usuario;

@Observable
public class BuscadorEventos {
	private Date fechaDesde; // Si queremos que sea LocalDateTime tenemos que convertirlo
	private Date fechaHasta;

	private List<AsistenciaEvento> resultados;
	private List<Evento> eventos;
	private String nombre;
	private String lugar;
	private boolean noTieneSugerencia;
	private LocalDateTime fecha;

	private Usuario usuario = new Usuario(); //Si no tiene un usuario rompe

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// ACCIONES
	public LocalDateTime convertirALocalDateTime(Date fecha) {
		try{
			LocalDateTime miFecha = LocalDateTime.ofInstant(fecha.toInstant(), ZoneId.systemDefault());
			return miFecha;
		}catch (Exception e){
			throw new EventoException("Alguna de las fechas ingresadas no es valida");
		}

	}

	public void search() {
		this.resultados = getEventosEntre(fechaDesde, fechaHasta);
	}

	public List<AsistenciaEvento> getEventosEntre(Date fechaDesde, Date fechaHasta) {
		LocalDateTime fechaInicio = convertirALocalDateTime(fechaDesde);
		LocalDateTime fechaFin = convertirALocalDateTime(fechaHasta);
		Set<AsistenciaEvento> setEventos = usuario.getCalendarioEventos().obtenerEventosEntre(fechaInicio, fechaFin);
		List<AsistenciaEvento> resultados = new ArrayList<>(setEventos);
		return resultados;
	}

	public void clear() {
		this.fechaDesde = null;
		this.fechaHasta = null;
		this.resultados = new ArrayList<>();
	}

	public void agregarEvento(String nombre, String ubicacion, Date fecha, Frecuencia frecuencia){
		eventos.add(new Evento(nombre, convertirALocalDateTime(fecha), ubicacion, frecuencia));
	}

	public void quitarEvento(Evento evento){
		eventos.remove(evento);
		usuario.quitarEvento(evento);
	}

	// ACCESORS

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaInicio) {
		this.fechaDesde = fechaInicio;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaFin) {
		this.fechaHasta = fechaFin;
	}

	public List<AsistenciaEvento> getResultados() {
		return resultados;
	}

	public void setResultados(List<AsistenciaEvento> resultados) {
		this.resultados = resultados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public boolean getTieneSugerencia() {
		return noTieneSugerencia;
	}

	public void setTieneSugerencia(boolean tieneSugerencia) {
		this.noTieneSugerencia = tieneSugerencia;
	}
}