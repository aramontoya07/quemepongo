package excepciones;

public class GuardadoDeImagenException extends RuntimeException {

    String formato;

    private static final long serialVersionUID = 15L;
    GuardadoDeImagenException(String formato){

    }
    @Override
    public String toString() {
        return "Error: No se pudo guardar la imagen en formato "+formato;
    }
}