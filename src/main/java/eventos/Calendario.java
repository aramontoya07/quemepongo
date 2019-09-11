package eventos;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import atuendo.SugerenciasClima;
import excepciones.EventoException;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import db.EntidadPersistente;
import usuario.Usuario;
@Entity
public class Calendario extends EntidadPersistente{
	@Transient
	private Scheduler scheduler;
	@OneToMany
	private Set<AsistenciaEvento> eventos = new HashSet<>();
	
	public Calendario(){
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void quitarEvento(Evento evento){
		eventos = eventos.stream()
				.filter(asistenciaEvento -> !asistenciaEvento.esDeEvento(evento))
				.collect(Collectors.toSet());

		try {
			scheduler.deleteJob(new JobKey("Job" + evento.getNombre()));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarEvento(Evento evento, Usuario user){
		AsistenciaEvento nuevaAsistencia = new AsistenciaEvento(evento);

		eventos.add(nuevaAsistencia);

		try {
			scheduler.getContext().put("usuario", user);
			scheduler.getContext().put("AsistenciaEvento", nuevaAsistencia);
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}

		JobDetail job = JobBuilder.newJob(ActivadorEvento.class)
				.withIdentity("Job" + evento.getNombre(), "Eventos")
				.build();

		Trigger trigger = evento.getActivador();

		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

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
