package eventos;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

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
		try {
			scheduler.getContext().put("usuario", user);
			scheduler.getContext().put("evento", evento);
		} catch (SchedulerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JobDetail job = JobBuilder.newJob(AsistenciaEvento.class)
			      .withIdentity("Job" + evento.getNombre(), "Eventos")
			      .build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
			      .withIdentity("Trigger" + evento.getNombre(), "ActivadoresEventos")
			      .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))        
			      .build();

		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public Set<Evento> getEventos(){
		   try {
			   for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals("Eventos"))) {		
				   String jobName = jobKey.getName();
				   System.out.println("[Nombre evento] : " + jobName);
			   }

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return new HashSet<Evento>();
	}
	
}
