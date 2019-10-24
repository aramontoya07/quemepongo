package Server;

import java.util.Set;

import atuendo.Atuendo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Guardarropa;
import usuario.RepoUsuarios;
import usuario.Usuario;

public class ControllerUsuario{

    Usuario usuarioPrueba;

        public ControllerUsuario(){
            usuarioPrueba = (new SetUpUsuario()).setear();
            RepoUsuarios.persistirUsuario(usuarioPrueba);
        }

        public Usuario obtenerUsuario(String idUsuario){
            return usuarioPrueba;
        }

        public ModelAndView listarGuardarropas(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario);
            // List<Guardarropa> guardarropas = new ArrayList<Guardarropa>(); //@TODO:
            // obtener guardarropas del usuario logueado
            return new ModelAndView(usuario, "misGuardarropas.hbs");
        }

        public ModelAndView listarEventos(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario);
            return new ModelAndView(usuario, "misEventos.hbs");
        }

        public ModelAndView listarEventosPorFecha(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario);
            return new ModelAndView(usuario, "eventosPorFecha.hbs");
        }

        public ModelAndView listarAceptados(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario);
            Set<Atuendo> atuendos = usuario.getAceptados();
            return new ModelAndView(atuendos, "misAtuendosAceptados.hbs");
        }

        public ModelAndView perfil(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario); 
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
            
            Usuario usuario = obtenerUsuario("0");

            if(!existeUsuario){
                res.redirect("/landing");
            }else{
                req.session().attribute("idUsuario", usuario.getId());
                res.redirect("/perfil/" + usuario.getId());
            }
            return null;
        }

        public ModelAndView puntuarAtuendo(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = null; //@TODO: obtener usuario logueado
            
            //@TODO: agregar adapatacion puntuada recibida por params al usuario logueado

            res.redirect("/puntuarAtuendos");
            return null;
        }
}
