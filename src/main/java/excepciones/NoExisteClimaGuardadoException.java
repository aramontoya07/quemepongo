package excepciones;

public class NoExisteClimaGuardadoException extends Throwable {
    private static final long serialVersionUID = 0L;

    @Override
    public String toString() {
        return "Error: No hay un clima con esa ciudad guardada.";
    }
}
