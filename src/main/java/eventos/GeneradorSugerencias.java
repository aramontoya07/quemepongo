package eventos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositorios.RepositorioUsuarios;
import usuario.Usuario;

import java.util.List;

public class GeneradorSugerencias {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneradorSugerencias.class);

    public static void main(String[] args) {
        List<Usuario> usuarios = RepositorioUsuarios.obtenerUsuariosTotales();

        usuarios.forEach(Usuario::generarSugerenciasNecesarias);
        LOGGER.info("Genere sugerencias :");
    }
}
