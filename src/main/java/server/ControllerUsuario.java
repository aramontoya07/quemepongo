package server;

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
import utils.JwtManager;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerUsuario {
    private static final String ID_USUARIO = "idUsuario";
    private static final String ID_ATUENDO = "idAtuendo";
    private JwtManager jwtManager;
    private Server server;


    public ControllerUsuario() {
        this.jwtManager = JwtManager.instancia();
        this.server = Server.instancia();
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
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
        String idAtuendo = req.params(ID_ATUENDO);

        UsoAtuendo uso = obtenerUsoAtuendo(usuario, idAtuendo);
        return new ModelAndView(uso, "puntuadorAtuendos.hbs");
    }

    public ModelAndView listarGuardarropas(Request req, Response res){
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
        return new ModelAndView(usuario, "misGuardarropas.hbs");
    }

    public ModelAndView agregarGuardarropas(Request req, Response res){
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }

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
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }

        Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();
        List<LocalDateTime> fechasDate = asistencias.stream()
                .map(asistencia -> asistencia.getFecha()).collect(Collectors.toList());
        List<String> fechass = fechasDate.stream().map(fecha -> fecha.toString()).collect(Collectors.toList());
        List<String> fechas = fechass.stream().map(fecha -> parsearFecha(fecha)).collect(Collectors.toList());

        model.put(ID_USUARIO, idUsuario);
        model.put("asistencias", asistencias);
        model.put("fechas", fechas);
        return new ModelAndView(model, "misEventos.hbs");

        }

    public ModelAndView listarEventosPorFecha(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }

        Integer dia = new Integer(req.params("dia"));
        Integer mes = new Integer(req.params("mes"));
        Integer anio = new Integer(req.params("anio"));
        Set<AsistenciaEvento> asistencias = usuario.getCalendarioEventos().getEventos();
        Set<AsistenciaEvento> asistenciasFiltradas = asistencias.stream().filter(asistencia -> asistencia.esDeFecha(dia, mes, anio)).collect(Collectors.toSet());
        model.put(ID_USUARIO, idUsuario);
        model.put("asistencias", asistenciasFiltradas);
        model.put("fecha", dia + "-" + mes + "-" + anio);
        return new ModelAndView(model, "misEventos.hbs");

    }

    public ModelAndView listarAceptados(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
        model.put("aceptados",usuario.getAceptados());
        return new ModelAndView(model, "misAtuendosAceptados.hbs");

    }

    public ModelAndView perfil(Request req, Response res) {

        try {
            Map<String, Object> model = new HashMap<String, Object>();
            String idUsuario = req.session().attribute(ID_USUARIO);
            Usuario usuario = obtenerUsuario(idUsuario);
            if(!server.getTokens().contains(req.cookie("token"))) {
                return new ModelAndView(model, "error403.hbs");
            }
            return new ModelAndView(usuario, "perfil.hbs");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ModelAndView actualizarPerfil(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }

        try{
        String contrasenia = req.queryParams("inputContrasenia");
        String mail = req.queryParams("inputEmail");
        String nombre = req.queryParams("inputNombre");
        String contraseniaRepetida = req.queryParams("inputRepeticionContrasenia");
        String imagen =  req.queryParams("inputImagen");
        if(!contrasenia.equals(contraseniaRepetida)) {
            res.body("Las constrasenias ingresadas deben coincidir");
            res.redirect("/actualizarPerfil");
            return null;
        }
        List<Usuario> usuariosEncontrados = RepositorioUsuarios.obtenerUsuarioPorNombre(nombre);
        if(usuariosEncontrados.size() != 0) {
            if(usuariosEncontrados.get(0)== usuario) {
                usuario.setNombre(nombre);
            } else {
                res.body("Ya existe un usuario con ese nombre");
            }
        } else {
            usuario.setNombre(nombre);
        }

        List<Usuario> usuariosEncontradosMail = RepositorioUsuarios.obtenerUsuariosPorMail(mail);
        if(usuariosEncontrados.size() != 0) {
            if(usuariosEncontradosMail.get(0)== usuario) {
                usuario.setMail(mail);
            } else {
                res.body("Ya existe un usuario con ese mail");
            }
        } else {
            usuario.setMail(mail);
        }
        usuario.setContrasenia(contrasenia);
        usuario.setRutaFotoPerfil(imagen);

            EntityManagerHelper.beginTransaction();
            EntityManagerHelper.getEntityManager().persist(usuario);
            EntityManagerHelper.commit();
            req.session().attribute(ID_USUARIO, Integer.toString(usuario.getId()));
            res.redirect("/perfil");
            return null;
        }catch(RepositorioException e){
            e.printStackTrace();
            res.redirect("/perfil");
            return null;
        }
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
            res.redirect("/");
            return null;
        }catch(RepositorioException e){
            e.printStackTrace();
            res.body("Ese nombre de usuario ya esta en uso");
            res.redirect("/registro");
            return null;
        }
    }

    public ModelAndView actualizacion(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        try {
            Usuario usuario = obtenerUsuario(idUsuario);
            if(!server.getTokens().contains(req.cookie("token"))) {
                return new ModelAndView(model, "error403.hbs");
            }
            return new ModelAndView(usuario, "nuevoPerfil.hbs");
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

   /* public ModelAndView loguearUsuario(Request req, Response res){
        String contrasenia = req.queryParams("inputPassword");
        String mail = req.queryParams("inputEmail");
        try{
            Usuario usuarioBuscado = RepositorioUsuarios.obtenerUsuarioPorMailYContra(mail,contrasenia);
            if(usuarioBuscado == null) {
                res.body("Mail o password incorrectos");

                return null;
            } else {
                req.session().attribute(ID_USUARIO, Integer.toString(usuarioBuscado.getId()));
                res.redirect("/perfil");
                return null;
            }
        }catch(RepositorioException e){
            e.printStackTrace();
            res.body("El usuario ingresado no existe");
            res.redirect("/");
            return null;
        }
    }*/

    public ModelAndView loguearUsuario(Request req, Response res){

        try {
            String password = req.queryParams("inputPassword");
            String mail = req.queryParams("inputEmail");

            Usuario usuarioBuscado = RepositorioUsuarios.obtenerUsuarioPorMailYContra(mail,password);

            if (usuarioBuscado.convertirSHA256(password).equals(usuarioBuscado.getContrasenia())) {
                req.session().attribute(ID_USUARIO, Integer.toString(usuarioBuscado.getId()));
                String token = UUID.randomUUID().toString();
                server.agregarToken(token);
                res.cookie("token", token, 3600);
                res.redirect("/perfil");
            } else
                res.body("Username or password wrong.");
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ModelAndView logout(Request req, Response res){
        try {
            String token = req.cookie("token");
            if (server.removerToken(token)) {
                res.removeCookie("token");
                res.body( "Successfully logged out.");
                System.out.println(token);
                res.redirect("/");
            } else
                res.body("You were not logged in.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ModelAndView puntuarAtuendo(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }

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

        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
        String idEvento = req.params("idEvento");
        String idAtuendo = req.queryParams("idAtuendo");
        try {
            Atuendo atuendomaxi = RepositorioAtuendos.obtenerAtuendo(idAtuendo);
            EntityManagerHelper.beginTransaction();
            usuario.aceptarAtuendo(atuendomaxi); //deberia persistirse el cambio
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();
        } catch(Exception e){
            System.out.println("El atuendo de id" + " " + idAtuendo + " " + "no existe");
            e.printStackTrace();
        }
        res.redirect("/misEventos/" + idEvento);
        return null;
    }

    public ModelAndView subscripcionPremium(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }

        EntityManagerHelper.beginTransaction();
        usuario.actualizarSubscripcionAPremium();
        EntityManagerHelper.commit();
        res.redirect("/perfil");
        return null;
    }

    public ModelAndView rechazarSugerencia(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
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
