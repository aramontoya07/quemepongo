package eventos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


public class Evento {
	private String tituloEvento;
    private LocalDateTime fecha;
    private String ubicacion;
    private Frecuencia frecuencia;
    private final int horasEventoCercano = 12;

    public Evento(String titulo,LocalDateTime fecha, String ubicacion, Frecuencia frecuencia) {
    	this.frecuencia = frecuencia;
    	this.tituloEvento = titulo;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }
    
	public boolean esEventoLejano() {
		return fecha.isBefore(LocalDateTime.now().minusHours(horasEventoCercano));
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public String getNombre() {
		return tituloEvento;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	
	public Trigger getActivador() {
		return frecuencia.getTrigger(this.fechaSugerencias(), tituloEvento);
	}


	public LocalDateTime fechaSugerencias() {
    	return fecha.minusHours(12);
    }
}
