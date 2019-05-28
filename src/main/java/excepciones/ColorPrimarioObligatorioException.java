package excepciones;

public class ColorPrimarioObligatorioException extends RuntimeException{
	private static final long serialVersionUID = 8L;
	
	@Override
	public String toString() {
		return "Error: Color primario es obligatorio";
	}
}
