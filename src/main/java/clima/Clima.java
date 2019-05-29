package clima;

public class Clima {
	double temperatura;
	public int constanteTermica = 100;

	public Clima(double temperatura) {
		super();
		this.temperatura = temperatura;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public double nivelAbrigoRequerido() {
		return constanteTermica - temperatura;
	}
}
