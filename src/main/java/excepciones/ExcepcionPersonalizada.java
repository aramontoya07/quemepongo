package excepciones;

public abstract class ExcepcionPersonalizada extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String mensajeError;

    public ExcepcionPersonalizada(String msj){
        mensajeError = msj;
    }

    @Override
    public String toString() {
        return "Error: " + mensajeError;
    }
}