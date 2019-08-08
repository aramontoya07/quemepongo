package excepciones;

public class PrendaFaultException extends RuntimeException{

    String mensaje;

    public PrendaFaultException(String mensaje){
        this.mensaje = mensaje;
    }

    private static final long serialVersionUID = 10L;

    @Override
    public String toString() {
        return "Error: " + mensaje;
    }
}
