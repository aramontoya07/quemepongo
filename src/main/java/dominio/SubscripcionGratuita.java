package dominio;

public class SubscripcionGratuita implements TipoSubscripcion {
	private int cantidadMaxima;
	public boolean puedoAgregar(int numeroDePrendas) {
		return numeroDePrendas < cantidadMaxima;
	}
}
