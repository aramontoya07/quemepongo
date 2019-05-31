package clima;

import java.time.Duration;
import java.time.LocalDateTime;

public class Clima {

	double temp;
	LocalDateTime fechaObtencion;

	public int constanteTermica = 40;

	public Clima(double temperatura) {
		this.temp = temperatura;
		this.fechaObtencion = LocalDateTime.now();
	}

	public boolean esValido() {
		return Duration.between(fechaObtencion,LocalDateTime.now()).toHours() <= 12;
		// 									INICIAL -> FINAL
	}

	public double nivelAbrigoRequerido() {
		return constanteTermica - temp;
	}

	public double getTemperatura() {
		return temp;
	}

	public void setTemperatura(double temperatura) {
		this.temp = temperatura;
	}

	public void setFechaObtencion(LocalDateTime date){
		this.fechaObtencion = date;
	}

	@Override
	public String toString() {
		return "La temperatura guardada es: " + temp + " con la fecha: " + fechaObtencion;
	}
}