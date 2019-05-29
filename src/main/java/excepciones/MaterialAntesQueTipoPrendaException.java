package excepciones;

public class MaterialAntesQueTipoPrendaException extends RuntimeException {
	private static final long serialVersionUID = 2L;

	@Override
	public String toString() {
		return "Error: Se debe definir el tipo de prenda antes de elegir un material";
	}

}
