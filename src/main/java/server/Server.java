package server;


import db.EntityManagerHelper;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.concurrent.Executors;

public class Server{
    public static void main(String[] args) {
        Spark.port(getHerokuAssignedPort());

        Spark.init();

        ControllerSistema sistemaC = new ControllerSistema();
        ControllerUsuario usuarioC =new ControllerUsuario();
        ControllerGuardarropas guardarropasC =new ControllerGuardarropas();
        ControllerEventos eventosC =new ControllerEventos();

        Spark.after((req, res) -> EntityManagerHelper.closeEntityManager());

        Spark.get("/", sistemaC::landing, new HandlebarsTemplateEngine());
        Spark.get("/registro", sistemaC::registro, new HandlebarsTemplateEngine());
        Spark.get("/perfil", usuarioC::perfil, new HandlebarsTemplateEngine());

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

        Spark.post("/registro", usuarioC::registrarUsuario, new HandlebarsTemplateEngine());
        Spark.post("/login", usuarioC::loguearUsuario, new HandlebarsTemplateEngine());
        Spark.post("/misGuardarropas/:idGuardarropas/creadorPrendas", guardarropasC::agregarPrenda, new HandlebarsTemplateEngine());
        Spark.post("/creadorEventos", eventosC::agregarEvento, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/aceptarSugerencia", usuarioC::aceptarSugerencia, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/rechazarSugerencia", usuarioC::rechazarSugerencia, new HandlebarsTemplateEngine());
        Spark.post("/puntuarAtuendos/:idAtuendo", usuarioC::puntuarAtuendo, new HandlebarsTemplateEngine());
        
        Spark.get("/favicon.ico", null, new HandlebarsTemplateEngine()); 

        if (System.getenv("QUE_ME_PONGO_ENV").equals("DEVELOPMENT")) {
            DebugScreen.enableDebugScreen();
        }
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}