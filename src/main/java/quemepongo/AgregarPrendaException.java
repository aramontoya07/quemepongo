package quemepongo;

public class AgregarPrendaException extends RuntimeException {

	@Override
	public String toString() {
		return "Error: El guardarropa al que intentaste agregar la prenda esta lleno";
	}
}