package eventos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import usuario.Usuario;

public class Calendario{
	private Scheduler scheduler;
	
	
	public Calendario(){
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarEvento(Evento evento, Usuario user){
		Map<String, Usuario> contextMap = new HashMap<String, Usuario>();
		contextMap.put("usuario", user);
		contextMap.put("evento", evento);
		JobDataMap contexto = new JobDataMap(contextMap);

		JobDetail job = JobBuilder.newJob(AsistenciaEvento.class)
			      .withIdentity("JobNombreEvento", "Eventos")
			      .usingJobData(contexto)
			      .build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
			      .withIdentity("TriggerNombreEvento", "ActivadoresEventos")
			      .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))        
			      .build();

		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public Set<Evento> getEventos(){
		
		return new HashSet<Evento>();
	}
	
}
