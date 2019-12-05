package server;

import java.util.List;
import repositorios.RepositorioUsuarios;
import usuario.Usuario;


public class GeneradorSugerenciasEventos {

    public static void main(String[] args) throws InterruptedException {
        while(true){
            Thread.sleep(600000); //cada 10 minutos
            List<Usuario> usuarios = RepositorioUsuarios.ObtenerUsuariosTotales();
            
            usuarios.forEach(usuario -> usuario.generarSugerenciasNecesarias());
        }
    }
}