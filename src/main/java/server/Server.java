package server;


import db.EntityManagerHelper;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class Server{

    private static Server instancia = null;
    private List<String> tokens = new ArrayList<>();

    public static Server instancia(){
        if(instancia == null){
            instancia = new Server();
        }
        return instancia;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void agregarToken(String token) {
        tokens.add(token);
    }

    public boolean removerToken(String token) {
        return tokens.remove(token);
    }

    public static void main(String[] args) {
        Spark.port(getAssignedPort());
        Spark.init();

        ControllerSistema sistemaC = new ControllerSistema();
        ControllerUsuario usuarioC =new ControllerUsuario();
        ControllerGuardarropas guardarropasC =new ControllerGuardarropas();
        ControllerEventos eventosC =new ControllerEventos();

        //Spark.staticFileLocation("public");

        /*Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });*/

        Spark.before("/*", (q, a) -> EntityManagerHelper.getEntityManager());
        Spark.after("/*", (q, a) -> EntityManagerHelper.closeEntityManager());

        Spark.get("/", sistemaC::landing, new HandlebarsTemplateEngine());
        Spark.get("/registro", sistemaC::registro, new HandlebarsTemplateEngine());
        Spark.post("/registro", usuarioC::registrarUsuario, new HandlebarsTemplateEngine());
        Spark.post("/login", usuarioC::loguearUsuario, new HandlebarsTemplateEngine());

        Spark.get("/perfil", usuarioC::perfil, new HandlebarsTemplateEngine());
        Spark.get("/actualizarFoto", usuarioC::perfil, new HandlebarsTemplateEngine());
        Spark.get("/actualizarNombre", usuarioC::perfil, new HandlebarsTemplateEngine());
        Spark.get("/actualizarContra", usuarioC::perfil, new HandlebarsTemplateEngine());
        Spark.get("/actualizarMail", usuarioC::perfil, new HandlebarsTemplateEngine());
        Spark.get("/actualizarSuscripcion", usuarioC::subscripcionPremium, new HandlebarsTemplateEngine());
        Spark.get("/actualizarPerfil", usuarioC::actualizacion, new HandlebarsTemplateEngine());
        Spark.post("/actualizarPerfil", usuarioC::actualizarPerfil, new HandlebarsTemplateEngine());

        Spark.get("/misGuardarropas", usuarioC::listarGuardarropas, new HandlebarsTemplateEngine());
        Spark.get("/misGuardarropas/:idGuardarropas", guardarropasC::detalleGuardarropa, new HandlebarsTemplateEngine());
        Spark.get("/misGuardarropasNuevo", usuarioC::agregarGuardarropas, new HandlebarsTemplateEngine());
        Spark.get("/misGuardarropas/:idGuardarropas/creadorPrendas", sistemaC::creadorPrendas, new HandlebarsTemplateEngine());
        Spark.get("/tipo", guardarropasC::wizardTipoPrenda, new HandlebarsTemplateEngine()); // :idGuardarropas/creadorPrendas/

        Spark.get("/caracteristicas", guardarropasC::wizardCaracteristicas, new HandlebarsTemplateEngine());
        Spark.get("/imagen", guardarropasC::wizardAdjuntarImagen, new HandlebarsTemplateEngine());

        Spark.get("/misEventos", usuarioC::listarEventos, new HandlebarsTemplateEngine());
        Spark.get("/misEventos/:mes/:dia/:anio", usuarioC::listarEventosPorFecha, new HandlebarsTemplateEngine());
        Spark.get("/misEventos/:idEvento", eventosC::detalleEvento, new HandlebarsTemplateEngine());
        Spark.get("/misEventos/:idEvento/generarSugerencias", eventosC::generarSugerencias, new HandlebarsTemplateEngine());
        Spark.get("/creadorEventos", sistemaC::creadorEventos, new HandlebarsTemplateEngine());

        Spark.get("/puntuarAtuendos", usuarioC::listarAceptados, new HandlebarsTemplateEngine());
        Spark.get("/puntuarAtuendos/:idAtuendo", usuarioC::puntuadorAtuendos, new HandlebarsTemplateEngine());


        Spark.get("/logout", usuarioC::logout, new HandlebarsTemplateEngine());
        Spark.post("/misGuardarropas/:idGuardarropas/creadorPrendas", guardarropasC::agregarPrenda, new HandlebarsTemplateEngine());
        Spark.post("/creadorEventos", eventosC::agregarEvento, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/aceptarSugerencia", usuarioC::aceptarSugerencia, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/rechazarSugerencia", usuarioC::rechazarSugerencia, new HandlebarsTemplateEngine());
        Spark.post("/puntuarAtuendos/:idAtuendo", usuarioC::puntuarAtuendo, new HandlebarsTemplateEngine());
        Spark.get("*",sistemaC::error404, new HandlebarsTemplateEngine());
        Spark.get("/favicon.ico", null, new HandlebarsTemplateEngine()); 

        DebugScreen.enableDebugScreen();
    }
    static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}