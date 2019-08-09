package alertas;

import org.quartz.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class ConsultarAlertas implements Job {
    private Scheduler scheduler;
    private static String frecuencia = "0 0 0/3 1/1 * ? *";


    @Override
    public void execute(JobExecutionContext contexto) {
        try {
            Alertador.generarSugerenciasParaEvento(usuario);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void empezarAVerificarAlertas(){

        JobDetail job = JobBuilder.newJob(ConsultarAlertas.class)
                .withIdentity("Job", "Eventos")
                .build();

        Trigger trigger = this.getActivador();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private Trigger getActivador() {
        return TriggerBuilder.newTrigger()
                .withIdentity("Trigger", "ActivadoresEvento")
                .startAt(this.fechaEnDate())
                .withSchedule(CronScheduleBuilder.cronSchedule(frecuencia))
                .build();
    }

    private Date fechaEnDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
