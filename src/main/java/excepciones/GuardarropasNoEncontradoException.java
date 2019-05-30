package excepciones;

public class GuardarropasNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 14L;

    @Override
    public String toString() {
        return "Error: No tengo este guardarropa";
    }
}
