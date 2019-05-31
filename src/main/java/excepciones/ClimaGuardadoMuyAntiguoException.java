package excepciones;

public class ClimaGuardadoMuyAntiguoException extends RuntimeException {

    @Override
    public String toString() {
        return "Error: Clima guardado ya no es valido";
    }
}
