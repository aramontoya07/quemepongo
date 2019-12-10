package server;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import atuendo.UsoAtuendo;
import db.EntityManagerHelper;
import eventos.AsistenciaEvento;
import excepciones.RepositorioException;
import prenda.ParteAbrigada;
import repositorios.RepositorioAtuendos;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Guardarropa;
import usuario.Usuario;

public class ControllerUsuario {
    private static final String ID_USUARIO = "idUsuario";
    private static final String ID_ATUENDO = "idAtuendo";

    public ControllerUsuario() {
    }

    public Usuario obtenerUsuario(String idUsuario) {
        return RepositorioUsuarios.obtenerUsuario(idUsuario);
    }

    public UsoAtuendo obtenerUsoAtuendo(Usuario usuario, String idAtuendo) {
        int id = Integer.parseInt(idAtuendo);
        Set<UsoAtuendo> usos = usuario.getAceptados();
        return usos.stream().filter(uso -> uso.getAtuendo().mismaId(id)).findFirst().get();
    }

    public ModelAndView puntuadorAtuendos(Request req, Response res) {
        String idUsuario = req.session().attribute(ID_USUARIO);
        String idAtuendo = req.params(ID_ATUENDO);
        Usuario usuario = obtenerUsuario(idUsuario);
        UsoAtuendo uso = obtenerUsoAtuendo(usuario, idAtuendo);
        return new ModelAndView(uso, "puntuadorAtuendos.hbs");
    }

    public ModelAndView listarGuardarropas(Request req, Response res){
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        return new ModelAndView(usuario, "misGuardarropas.hbs");
    }

    public ModelAndView agregarGuardarropas(Request req, Response res){
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        EntityManagerHelper.beginTransaction();
        usuario.agregarGuardarropa(new Guardarropa());
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        res.redirect("/misGuardarropas");
        return null;
    }

    private String parsearFecha(String fechaRaw){
        return  fechaRaw.substring(5, 7) + "/" + fechaRaw.substring(8, 10)  + "/" + fechaRaw.substring(0, 4);
    }

    public ModelAndView listarEventos(Request req, Response res) {
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();
        List<LocalDateTime> fechasDate = asistencias.stream()
            .map(asistencia -> asistencia.getFecha()).collect(Collectors.toList());
        List<String> fechass = fechasDate.stream().map(fecha -> fecha.toString()).collect(Collectors.toList());
        List<String> fechas = fechass.stream().map(fecha -> parsearFecha(fecha)).collect(Collectors.toList());
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(ID_USUARIO,idUsuario);
        model.put("asistencias", asistencias);
        model.put("fechas", fechas);
    return new ModelAndView(model, "misEventos.hbs");
        }

        public ModelAndView listarEventosPorFecha(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
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
            String idUsuario = req.session().attribute(ID_USUARIO);
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
                res.body("Las constrasenias ingresadas deben coincidir");
                res.redirect("/registro");
                return null;
            }
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setMail(mail);
            usuario.setContrasenia(contrasenia);
            try{
                RepositorioUsuarios.persistirUsuario(usuario);
                req.session().attribute(ID_USUARIO, Integer.toString(usuario.getId()));
                res.redirect("/perfil");
                return null;
            }catch(RepositorioException e){
                res.body("Ese nombre de usuario ya esta en uso");
                res.redirect("/registro");
                return null;
            }
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
                res.body("El usuario ingresado no existe");
                res.redirect("/");
                return null;
            }
        }

        public ModelAndView puntuarAtuendo(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            String idAtuendo = req.params("idAtuendo");
            UsoAtuendo uso = obtenerUsoAtuendo(usuario, idAtuendo);

            int puntajeCabeza = Integer.parseInt(req.queryParams("cabezaPuntaje"));
            int puntajeCuello = Integer.parseInt(req.queryParams("cuelloPuntaje"));
            int puntajePecho = Integer.parseInt(req.queryParams("pechoPuntaje"));
            int puntajeManos = Integer.parseInt(req.queryParams("manosPuntaje"));
            int puntajePiernas = Integer.parseInt(req.queryParams("piernasPuntaje"));
            int puntajePies = Integer.parseInt(req.queryParams("piesPuntaje"));

            EntityManagerHelper.beginTransaction();
            usuario.puntuarParteDeAtuendoEn(uso, puntajeCabeza, ParteAbrigada.CABEZA);
            usuario.puntuarParteDeAtuendoEn(uso, puntajeCuello, ParteAbrigada.CUELLO);
            usuario.puntuarParteDeAtuendoEn(uso, puntajePecho, ParteAbrigada.PECHO);
            usuario.puntuarParteDeAtuendoEn(uso, puntajeManos, ParteAbrigada.MANOS);
            usuario.puntuarParteDeAtuendoEn(uso, puntajePiernas, ParteAbrigada.PIERNAS);
            usuario.puntuarParteDeAtuendoEn(uso, puntajePies, ParteAbrigada.PIES);
            uso.setPuntuado(true);
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();
            
            res.redirect("/puntuarAtuendos");
            return null;
        }
        
        

        public ModelAndView aceptarSugerencia(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
            String idEvento = req.params("idEvento");
            String idAtuendo = req.queryParams("idAtuendo");
            Atuendo atuendomaxi = RepositorioAtuendos.obtenerAtuendo(idAtuendo);

            EntityManagerHelper.beginTransaction();
            usuario.aceptarAtuendo(atuendomaxi); //deberia persistirse el cambio
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();
            
            res.redirect("/misEventos/" + idEvento);
            return null;
        } 
        
        public ModelAndView subscripcionPremium(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
            
            EntityManagerHelper.beginTransaction();
            usuario.actualizarSubscripcionAPremium();
            EntityManagerHelper.commit();
            res.redirect("/perfil");
            return null;
        } 

        public ModelAndView rechazarSugerencia(Request req, Response res) {
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
            String idEvento = req.params("idEvento");
            String idAtuendo = req.queryParams("idAtuendo");
            Atuendo atuendomaxi = RepositorioAtuendos.obtenerAtuendo(idAtuendo);

            EntityManagerHelper.beginTransaction();
            usuario.rechazarAtuendo(atuendomaxi);
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();

            res.redirect("/misEventos/" + idEvento);
            return null;
        }
}
