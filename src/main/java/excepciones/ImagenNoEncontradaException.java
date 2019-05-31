package excepciones;

public class ImagenNoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 16L;

    @Override
    public String toString() {
        return "Error: No se pudo encontrar la imagen en la ruta especificada";
    }
}
