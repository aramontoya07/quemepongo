package eventos;

import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Unico implements Frecuencia{
	public Trigger getTrigger(LocalDateTime fecha, String tituloEvento) {
		return TriggerBuilder.newTrigger()
				.withIdentity("Trigger" + tituloEvento, "ActivadoresEvento")
				.startAt(this.fechaSugerenciasEnDate(fecha))
				.build();
	}

	private Date fechaSugerenciasEnDate(LocalDateTime fecha) {
		return Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
	}
}
