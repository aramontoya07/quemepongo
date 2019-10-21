package Server;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class ControllerUsuario {
    
        public Usuario obtenerUsuario(Integer idUsuario){
            Usuario usuario = new Usuario(); //@TODO: obtener usuario segun id de session
            return usuario;
        }

        public ModelAndView perfil(Request req, Response res) {
    
            Usuario usuario = new Usuario();
            //@TODO: obtener usuario de DB
            
            return new ModelAndView(usuario, "perfil.hbs");
        }

        public ModelAndView registrarUsuario(Request req, Response res) {

            Usuario usuario = new Usuario();
            //@TODO: crear usuario y agregar usuario a la base de datos

            req.session().attribute("idUsuario", usuario.getId());

            res.redirect("/perfil/" + usuario.getId());
            return null;
        }

        public ModelAndView loguearUsuario(Request req, Response res) {

            Boolean existeUsuario = true; //@TODO: controlar existencia en DB
            
            Usuario usuario = new Usuario(); //@TODO: obtener usuario de db

            if(!existeUsuario){
                res.redirect("/landing");
            }else{
                req.session().attribute("idUsuario", usuario.getId());
                res.redirect("/perfil/" + usuario.getId());
            }
            return null;
        }

        public ModelAndView listarGuardarropas(Request req, Response res) {
            Usuario usuario = obtenerUsuario(req.attribute("idUsuario"));
            return new ModelAndView(usuario, "guardarropas.hbs");
        }
}
