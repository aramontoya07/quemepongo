package Server;

import java.util.Set;

import atuendo.Atuendo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class ControllerUsuario {
    
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

        public ModelAndView listarAceptados(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = null; //@TODO: obtener usuario logueado
            Set<Atuendo> atuendos = usuario.getAceptados();
            return new ModelAndView(atuendos, "misAtuendosAceptados.hbs");
        }

        public ModelAndView puntuarAtuendo(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = null; //@TODO: obtener usuario logueado
            
            //@TODO: agregar adapatacion puntuada recibida por params al usuario logueado

            res.redirect("/puntuarAtuendos");
            return null;
        }
}
