package excepciones;

public class NingunaSugerenciaParaEventoException extends RuntimeException {
    private static final long serialVersionUID = 12L;

    @Override
    public String toString() {
        return "Error: No hay sugerencias para este evento.";
    }
}
