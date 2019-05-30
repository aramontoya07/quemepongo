package excepciones;

public class EventoLejanoException extends RuntimeException {
    private static final long serialVersionUID = 11L;

    @Override
    public String toString() {
        return "Error: Todavia no puedo generar una sugerencia para este evento.";
    }
}
