package eventos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class EventoUnico extends Evento{
	
    public EventoUnico(String titulo, LocalDateTime fecha, String ubicacion) {
		super(titulo, fecha, ubicacion);
	}

	@Override
	public Trigger getActivador() {
		return TriggerBuilder.newTrigger()
				  .withIdentity("Trigger" + tituloEvento, "ActivadoresEvento")
				  .startAt(Date.from(fechaSugerencias().atZone(ZoneId.systemDefault()).toInstant()))
				  .build();
	}
	

}
