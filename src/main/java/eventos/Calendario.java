package eventos;


import java.util.HashSet;
import java.util.Set;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import usuario.Usuario;

public class Calendario{
	private Scheduler scheduler;
	private Set<AsistenciaEvento> eventos = new HashSet<>();
	
	public Calendario(){
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
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
		
	public Set<AsistenciaEvento> getEventos(){
		return eventos;
	}
}
