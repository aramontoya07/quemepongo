package eventos;

import org.quartz.Trigger;

import java.time.LocalDateTime;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import java.time.ZoneId;
import java.util.Date;

public enum Frecuencia {
	UNICO(){
		public Trigger getTrigger(LocalDateTime fecha, String tituloEvento) {
			return TriggerBuilder.newTrigger()
					.withIdentity("Trigger" + tituloEvento, "ActivadoresEvento")
					.startAt(fechaSugerenciasEnDate(fecha))
					.build();
		}
		private Date fechaSugerenciasEnDate(LocalDateTime fecha) {
			return Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
		}

	},
	DIARIO(){
		public Trigger getTrigger(LocalDateTime fecha, String tituloEvento){
			return TriggerBuilder.newTrigger()
					.withIdentity("Trigger" + tituloEvento, "ActivadoresEvento")
					.startAt(fechaSugerenciasEnDate(fecha))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24))
					.build();
		}
		private Date fechaSugerenciasEnDate(LocalDateTime fecha) {
			return Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
		}
	};
}

