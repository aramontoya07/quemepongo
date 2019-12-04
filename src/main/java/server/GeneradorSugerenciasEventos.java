package server;

import repositorios.RepositorioUsuarios;
import usuario.Usuario;

import java.util.List;

public class GeneradorSugerenciasEventos {

    public static void main(String[] args) {
        List<Usuario> usuarios = RepositorioUsuarios.ObtenerUsuariosTotales();

        usuarios.forEach(usuario -> usuario.generarSugerenciasNecesarias());
    }
}
