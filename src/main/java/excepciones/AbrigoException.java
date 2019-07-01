package excepciones;

public class AbrigoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Error: La capa de abrigo que intentaste agregar no es válida para este atuendo";
	}
}
