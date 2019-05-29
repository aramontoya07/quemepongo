package excepciones;

public class TipoPrendaObligatorioException extends RuntimeException {
	private static final long serialVersionUID = 6L;

	@Override
	public String toString() {
		return "Error: Tipo de prenda es obligatorio";
	}

}
