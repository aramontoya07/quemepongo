package Server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import atuendo.UsoAtuendo;
import eventos.AsistenciaEvento;
import excepciones.RepositorioException;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class ControllerUsuario{

    Usuario usuarioPrueba;
    private static final String ID_USUARIO = "idUsuario";

        public ControllerUsuario(){
            usuarioPrueba = (new SetUpUsuario()).setear();
        }

        public Usuario obtenerUsuario(String idUsuario){
            return RepositorioUsuarios.obtenerUsuario(idUsuario);
        }

        public UsoAtuendo obtenerUsoAtuendo(Usuario usuario, String idAtuendo){
            int id = Integer.parseInt(idAtuendo);
            Set<UsoAtuendo> usos = usuario.getAceptados();
            return usos.stream().filter(uso -> uso.getAtuendo().mismaId(id)).findFirst().get(); //TODO: que pasa si no lo encuentro?
        }

        public ModelAndView puntuadorAtuendos(Request req, Response res){
            String idUsuario = req.attribute(ID_USUARIO);
            String idAtuendo = req.params("idAtuendo");
            Usuario usuario = obtenerUsuario(idUsuario);
            UsoAtuendo uso = obtenerUsoAtuendo(usuario, idAtuendo);
            return new ModelAndView(uso, "puntuadorAtuendos.hbs");
        }

        public ModelAndView listarGuardarropas(Request req, Response res) {
            String idUsuario = req.attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            // List<Guardarropa> guardarropas = new ArrayList<Guardarropa>(); //@TODO:
            // obtener guardarropas del usuario logueado
            return new ModelAndView(usuario, "misGuardarropas.hbs");
        }

        public ModelAndView listarEventos(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            Map<String, Object> model = new HashMap<String, Object>();
            
            Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();
            model.put(ID_USUARIO,idUsuario);
            model.put("asistencias", asistencias);

            return new ModelAndView(model, "misEventos.hbs");
        }

        public ModelAndView listarEventosPorFecha(Request req, Response res) {
            String idUsuario = req.attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            Integer dia = new Integer(req.params("dia"));
            Integer mes = new Integer(req.params("mes"));
            Integer anio = new Integer(req.params("anio"));

            Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();

            Set<AsistenciaEvento> asistenciasFiltradas = asistencias.stream().filter(asistencia -> asistencia.esDeFecha(dia,mes,anio)).collect(Collectors.toSet());

            Map<String, Object> model = new HashMap<String, Object>();
            model.put(ID_USUARIO, idUsuario);
            model.put("asistencias", asistenciasFiltradas);
            model.put("fecha", dia + "-" + mes + "-" + anio);

            return new ModelAndView(model, "misEventos.hbs");
        }

        public ModelAndView listarAceptados(Request req, Response res) {
            String idUsuario = req.attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("aceptados",usuario.getAceptados());
            return new ModelAndView(model, "misAtuendosAceptados.hbs");
        }

        public ModelAndView perfil(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario); 
            return new ModelAndView(usuario, "perfil.hbs");
        }

        public ModelAndView registrarUsuario(Request req, Response res) {
            String contrasenia = req.queryParams("inputContrasenia");
            String mail = req.queryParams("inputEmail");
            String nombre = req.queryParams("inputNombre");
            String contraseniaRepetida = req.queryParams("inputRepeticionContrasenia");

            if(!contrasenia.equals(contraseniaRepetida)) {
                res.redirect("/registro");
                return null;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setMail(mail);
            usuario.setContrasenia(contrasenia);

            RepositorioUsuarios.persistirUsuario(usuario);

            req.session().attribute(ID_USUARIO, Integer.toString(usuario.getId()));

            res.redirect("/perfil/" + usuario.getId());
            return null;
        }

        public ModelAndView loguearUsuario(Request req, Response res){
            String contrasenia = req.queryParams("inputPassword");
            String mail = req.queryParams("inputEmail");
            try{
                Usuario usuario = RepositorioUsuarios.obtenerUsuarioPorMailYContra(mail,contrasenia);
                req.session().attribute(ID_USUARIO, Integer.toString(usuario.getId()));
                res.redirect("/perfil");
                return null;
            }catch(RepositorioException e){
                res.redirect("/");
                return null;
            }
        }

        public ModelAndView puntuarAtuendo(Request req, Response res) {
            String idUsuario = req.attribute(ID_USUARIO);
            Usuario usuario = null; //@TODO: obtener usuario logueado
            
            //@TODO: agregar adapatacion puntuada recibida por params al usuario logueado

            res.redirect("/puntuarAtuendos");
            return null;
        }

        public ModelAndView aceptarSugerencia(Request req, Response res) {
            String idUsuario = req.attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            //String idEvento = req.params("idEvento");
            //String idAtuendo = req.params("idAtuendo");
            //Atuendo = obtenerAtuendo(idAtuendo)
            //usuario.aceptarAtuendo(atuendo);
            res.redirect("/misEventos/:idEvento");
            return null;
        }  

        public ModelAndView rechazarSugerencia(Request req, Response res) {
            String idUsuario = req.attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario); // @TODO: obtener usuario logueado
            String idAtuendo = req.params("idAtuendo");
            Atuendo atuendo = null; // @TODO: obtener atuendo indicado
            usuario.rechazarAtuendo(atuendo);
            res.redirect("/misEventos/:idEvento");
            return null;
        }
}
