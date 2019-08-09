package alertas;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class ConsultarAlertas implements Job {
    private Scheduler scheduler;
    String ubicacionElegida;

    @Override
    public void execute(JobExecutionContext contexto) {
            Alertador alertador = new Alertador();
            alertador.comprobarAlertas(/*ubicacionElegida*/);
    }

    public ConsultarAlertas(){
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void empezarAVerificarAlertas(String ubicacion){
        ubicacionElegida = ubicacion;
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
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(3))
                .build();
    }

    private Date fechaEnDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
