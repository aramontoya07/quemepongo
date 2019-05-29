package excepciones;

public class NoExistePrendaEnGuardarropaException extends RuntimeException {
private static final long serialVersionUID = 10L;
	
	@Override
	public String toString() {
		return "Error: No existe la prenda en el guardarropa";
	}
}
