package clima;

import java.time.Duration;
import java.time.LocalDate;

public class Clima {
	double temp;
	LocalDate fechaObtencion;
	public int constanteTermica = 100;

	public Clima(double temperatura) {
		super();
		this.temp = temperatura;
		this.fechaObtencion = LocalDate.now();
	}

	public double getTemperatura() {
		return temp;
	}

	public boolean esValido() {
		return Duration.between(fechaObtencion, LocalDate.now()).toHours() < 12;
	}

	public void setTemperatura(double temperatura) {
		this.temp = temperatura;
	}

	public double nivelAbrigoRequerido() {
		return constanteTermica - temp;
	}

	@Override
	public String toString() {
		return "El clima en la ciudad de: No tengo la ciudad, es: " + temp + " en la fecha: " + fechaObtencion;
	}
}