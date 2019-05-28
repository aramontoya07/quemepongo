package excepciones;

public class ColorSecundarioSinPrimarioException extends RuntimeException {
	private static final long serialVersionUID = 4L;
	
	@Override
	public String toString() {
		return "Error: Se debe definir un color primario antes de elegir uno secundario";
	}
	
}
