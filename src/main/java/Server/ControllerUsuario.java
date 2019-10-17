package Server;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

        public ModelAndView Usuarios(Request req, Response res) {
            Usuario usuario1 = new Usuario();
            Usuario usuario2 = new Usuario();
            Usuario usuario3 = new Usuario();

            usuario1.setMail("usuario1@gmail.com");
            usuario2.setMail("usuario2@gmail.com");
            usuario3.setMail("usuario3@gmail.com");

            List<Usuario> usuarioLista = new ArrayList<Usuario>();
            usuarioLista.add(usuario1);
            usuarioLista.add(usuario2);
            usuarioLista.add(usuario3);

            return new ModelAndView(usuarioLista, "hola.hbs");
        }
}
