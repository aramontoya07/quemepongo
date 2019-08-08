package eventos;

import org.quartz.Trigger;

import java.time.LocalDateTime;

public interface Frecuencia {
	
	public Trigger getTrigger(LocalDateTime fecha, String tituloEvento);
}

