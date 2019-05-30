package excepciones;

public class GuardarropasYaAgregadoException extends RuntimeException {
    private static final long serialVersionUID = 13L;

    @Override
    public String toString() {
        return "Error: El guardarropa ya esta agregado";
    }
}
