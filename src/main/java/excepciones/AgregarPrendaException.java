package excepciones;

public class AgregarPrendaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Error: El guardarropa al que intentaste agregar la prenda esta lleno";
	}
}