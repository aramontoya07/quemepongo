package excepciones;

public class PrendaYaExisteException extends RuntimeException {
    
    @Override
    public String toString() {
        return "Error: Ya existe esta prenda en el guardarropa :D";
    }
}
