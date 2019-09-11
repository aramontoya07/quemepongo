package eventos;

import java.time.LocalDateTime;

import org.quartz.Trigger;
import db.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Evento extends EntidadPersistente {
	private String tituloEvento;
    private LocalDateTime fecha;
    private String ubicacion;
    @Transient
    private Frecuencia frecuencia;

	public Evento(String titulo,LocalDateTime fecha, String ubicacion, Frecuencia frecuencia) {
    	this.frecuencia = frecuencia;
    	this.tituloEvento = titulo;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }
    
	boolean esEventoLejano() {
		int horasEventoCercano = 12;
		return fecha.isBefore(LocalDateTime.now().minusHours(horasEventoCercano));
	}
	
	LocalDateTime getFecha() {
		return fecha;
	}
	
	String getNombre() {
		return tituloEvento;
	}

	String getUbicacion() {
		return ubicacion;
	}
	
	Trigger getActivador() {
		return frecuencia.getTrigger(this.fechaSugerencias(), tituloEvento);
	}

	private LocalDateTime fechaSugerencias() {
    	return fecha.minusHours(12);
    }

    public boolean esEventoCercanoYOcurreEn(String ubicacionDada) {
		return esEventoCercano() && ubicacion.equals(ubicacionDada);
    }

	private boolean esEventoCercano() {
		return !esEventoLejano();
	}
}
