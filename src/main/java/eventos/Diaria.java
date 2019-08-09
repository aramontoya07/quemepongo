package eventos;

import org.quartz.CronScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Diaria implements Frecuencia{

	public Trigger getTrigger(LocalDateTime fecha, String tituloEvento) {
			return TriggerBuilder.newTrigger()
					.withIdentity("Trigger" + tituloEvento, "ActivadoresEvento")
					.startAt(this.fechaSugerenciasEnDate(fecha))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24))
					.build();
	}

	private Date fechaSugerenciasEnDate(LocalDateTime fecha) {
		return Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
	}
}
