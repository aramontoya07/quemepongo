package subscripciones;

public class SubscripcionGratuita implements TipoSubscripcion {
	private int cantidadMaxima = 5;

	public boolean puedoAgregar(int numeroDePrendas) {
		return numeroDePrendas < cantidadMaxima;
	}
}
