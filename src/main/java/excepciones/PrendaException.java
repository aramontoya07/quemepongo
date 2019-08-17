package excepciones;

public class PrendaException extends RuntimeException{

    String mensaje;

    public PrendaException(String mensaje){
        this.mensaje = mensaje;
    }

    //Para qué es esto?
    private static final long serialVersionUID = 10L;

    @Override
    public String toString() {
        return "Error: " + mensaje;
    }
}
