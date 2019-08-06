package excepciones;

public class PrendaFaultException extends RuntimeException{
    private static final long serialVersionUID = 10L;

    @Override
    public String toString() {
        return "Error: No existe la prenda solicitada";
    }
}
