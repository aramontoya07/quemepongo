package eventos;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import org.quartz.Trigger;

import db.EntidadPersistente;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
public class Evento extends EntidadPersistente {
	private String tituloEvento;
	private LocalDateTime fecha;
	private String ubicacion;
	public String fechaFormateada;
	@Enumerated
	private Frecuencia frecuencia;

	public Evento(String titulo, LocalDateTime fecha, String ubicacion, Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
		this.tituloEvento = titulo;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.fechaFormateada = fecha.getDayOfWeek().toString() + " " + fecha.getDayOfMonth() + " de "
				+ fecha.getMonth().toString() + " del " + fecha.getYear();
	}

	public String getFechaFormateada() {
		return fechaFormateada;
	}

	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}

	public Evento() {
	}

	public boolean esEventoLejano() {
		int horasEventoCercano = 12;
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

	public String getTituloEvento() {
		return tituloEvento;
	}

	public void setTituloEvento(String tituloEvento) {
		this.tituloEvento = tituloEvento;
	}
}
