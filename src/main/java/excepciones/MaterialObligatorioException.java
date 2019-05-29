package excepciones;

public class MaterialObligatorioException extends RuntimeException {
	private static final long serialVersionUID = 7L;

	@Override
	public String toString() {
		return "Error: Material es obligatorio";
	}
}
