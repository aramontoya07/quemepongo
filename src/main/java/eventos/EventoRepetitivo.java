package eventos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class EventoRepetitivo extends Evento{
	Frecuencia frecuencia;
	
	public EventoRepetitivo(String titulo, LocalDateTime fecha, String ubicacion,Frecuencia _frecuencia) {
		super(titulo, fecha, ubicacion);
		frecuencia = _frecuencia;
	}
	
	@Override
	public Trigger getActivador() {
		return TriggerBuilder.newTrigger()
				  .withIdentity("Trigger" + tituloEvento, "ActivadoresEvento")
				  .startAt(Date.from(fechaSugerencias().atZone(ZoneId.systemDefault()).toInstant()))
				  .withSchedule(CronScheduleBuilder.cronSchedule("asd"))
				  .build();
	}

}