package eventos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import atuendo.SugerenciasClima;
import excepciones.EventoException;

import db.EntidadPersistente;
import usuario.Usuario;

@Entity
@Table(name = "Calendarios")
public class Calendario extends EntidadPersistente{

	@OneToMany(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "Id_calendario")
	private Set<AsistenciaEvento> eventos = new HashSet<>();
	
	public Calendario(){
	}

	public AsistenciaEvento obtenerAsistencia(Evento evento){
		return eventos.stream().filter(asistenciaEvento -> asistenciaEvento.esDeEvento(evento)).findFirst().get();
	}

	public Set<AsistenciaEvento> getEventos() {
		return eventos;
	}

	public void generarSugerenciasNecesarias(Usuario usuario){
		eventos.stream()
				.filter(asistencia -> !asistencia.getEvento().esEventoLejano())
				.forEach(asistencia ->asistencia.generarSugerenciasParaEvento(usuario));
	}

	public void setEventos(Set<AsistenciaEvento> eventos) {
		this.eventos = eventos;
	}

	public void quitarEvento(Evento evento){
		eventos = eventos.stream()
				.filter(asistenciaEvento -> !asistenciaEvento.esDeEvento(evento))
				.collect(Collectors.toSet());
	}
	
	public void agregarEvento(Evento evento, Usuario user){
		AsistenciaEvento nuevaAsistencia = new AsistenciaEvento(evento);
		eventos.add(nuevaAsistencia);
	}

	public Set<AsistenciaEvento> obtenerEventosEntre(LocalDateTime  fechaMinima, LocalDateTime fechaMaxima){
		Set <AsistenciaEvento> asistencias = eventos.stream().filter(asistenciaEvento -> asistenciaEvento.ocurreEntre(fechaMinima, fechaMaxima)).collect(Collectors.toSet());
		if(asistencias.isEmpty()) throw new EventoException("No existen eventos entre las fechas indicadas");
		return asistencias;
	}

	public Set<SugerenciasClima> pedirSugerenciasParaEvento(Evento evento){
		return eventos.stream().filter(asistencia -> asistencia.getEvento().equals(evento)).findFirst().get().pedirSugerencias();
	}

	public boolean hayEventosCercanosEn(String ubicacion) {
		return eventos.stream().anyMatch(asistencia -> asistencia.esCercanaYEsEn(ubicacion));
	}
}
