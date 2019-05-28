package excepciones;

public class ColorSecundarioIgualAPrimarioException extends RuntimeException{
	private static final long serialVersionUID = 5L;
	
	@Override
	public String toString() {
		return "Error: El color secundario debe diferir del primario";
	}
}
