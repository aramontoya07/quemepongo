package Server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import eventos.AsistenciaEvento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.UsoAtuendo;
import usuario.Usuario;

public class ControllerUsuario{

    Usuario usuarioPrueba;

        public ControllerUsuario(){
            usuarioPrueba = (new SetUpUsuario()).setear();
            //RepoUsuarios.persistirUsuario(usuarioPrueba);
        }

        public Usuario obtenerUsuario(String idUsuario){
            return usuarioPrueba;
        }

        public UsoAtuendo obtenerAtuendo(Usuario usuario, String idAtuendo){
            Set<UsoAtuendo> usos = usuario.getAceptados();
            return usos.stream().filter( uso -> uso.getAtuendo().getId() == new Integer(idAtuendo) ).findFirst().orElse(null);
        }

        public ModelAndView puntuadorAtuendos(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            String idAtuendo = req.params("idAtuendo");
            Usuario usuario = obtenerUsuario(idUsuario);
            UsoAtuendo uso = obtenerAtuendo(usuario, idAtuendo);
            return new ModelAndView(uso, "puntuadorAtuendos.hbs");
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
            Map<String, Object> model = new HashMap<String, Object>();
            
            Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();
            model.put("idUsuario",idUsuario);
            model.put("asistencias", asistencias);

            return new ModelAndView(model, "misEventos.hbs");
        }

        public ModelAndView listarEventosPorFecha(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario);
            Integer dia = new Integer(req.params("dia"));
            Integer mes = new Integer(req.params("mes"));
            Integer anio = new Integer(req.params("anio"));

            Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();

            Set<AsistenciaEvento> asistenciasFiltradas = asistencias.stream().filter(asistencia -> asistencia.esDeFecha(dia,mes,anio)).collect(Collectors.toSet());

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("idUsuario", idUsuario);
            model.put("asistencias", asistenciasFiltradas);
            model.put("fecha", dia + "-" + mes + "-" + anio);

            return new ModelAndView(model, "misEventos.hbs");
        }

        public ModelAndView listarAceptados(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario);
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("aceptados",usuario.getAceptados());
            return new ModelAndView(model, "misAtuendosAceptados.hbs");
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

        public ModelAndView aceptarSugerencia(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = null; // @TODO: obtener usuario logueado
            String idAtuendo = req.params("idAtuendo");
            Atuendo atuendo = null; // @TODO: obtener atuendo indicado
            usuario.aceptarAtuendo(atuendo);
            res.redirect("/misEventos/:idEvento");
            return null;
        }  

        public ModelAndView rechazarSugerencia(Request req, Response res) {
            String idUsuario = req.attribute("idUsuario");
            Usuario usuario = obtenerUsuario(idUsuario); // @TODO: obtener usuario logueado
            String idAtuendo = req.params("idAtuendo");
            Atuendo atuendo = null; // @TODO: obtener atuendo indicado
            usuario.rechazarAtuendo(atuendo);
            res.redirect("/misEventos/:idEvento");
            return null;
        }
}
