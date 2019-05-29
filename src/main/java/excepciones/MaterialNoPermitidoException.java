package excepciones;

public class MaterialNoPermitidoException extends RuntimeException {
	private static final long serialVersionUID = 3L;

	@Override
	public String toString() {
		return "Error: El material no esta permitido para este tipo de prenda";
	}

}
