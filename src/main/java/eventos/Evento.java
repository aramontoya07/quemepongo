package eventos;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import org.quartz.Trigger;

import db.EntidadPersistente;

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
		this.fechaFormateada = traducirDia(fecha.getDayOfWeek().toString()) + " " + fecha.getDayOfMonth() + " de "
				+ traducirMes(fecha.getMonth().toString()) + " del " + fecha.getYear();
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

	private String traducirDia(String day) {
		switch (day) {
		case "MONDAY":
			return "lunes";
		case "TUESDAY":
			return "martes";
		case "WEDNESDAY":
			return "miercoles";
		case "THRUSDAY":
			return "jueves";
		case "FRIDAY":
			return "viernes";
		case "SATURDAY":
			return "sabado";
		case "SUNDAY":
			return "domingo";
		default:
			return "ERROR EN EL DIA";
		}
	}

	private String traducirMes(String month) {
		switch (month) {
		case "JANUARY":
			return "enero";
		case "FEBRUARY":
			return "febrero";
		case "MARCH":
			return "marzo";
		case "APRIL":
			return "abril";
		case "MAY":
			return "mayo";
		case "JUNE":
			return "junio";
		case "JULY":
			return "julio";
		case "AUGUST":
			return "agosto";
		case "SEPTEMBER":
			return "septiembre";
		case "OCTOBER":
			return "octubre";
		case "NOVEMBER":
			return "noviembre";
		case "DECEMBER":
			return "diciembre";
		default:
			return "ERROR EN EL MES";
		}
	}
}
