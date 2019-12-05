package server;

//import repositorios.RepositorioUsuarios;
//import usuario.Usuario;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;

public class GeneradorSugerenciasEventos {

    public static void main(String[] args) {
        Scheduler scheduler = null;
        
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        JobDetail job = JobBuilder.newJob(ActivadorGenerador.class).withIdentity("Job1" , "Grupo1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Trigger1", "Grupo1").startNow()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)).build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}