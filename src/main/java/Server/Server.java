package Server;

import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server{
    public static void main(String[] args) {

        Spark.port(9000);
        Spark.init();

        ControllerSistema sistemaC = new ControllerSistema();
        ControllerUsuario usuarioC =new ControllerUsuario();
        ControllerGuardarropas guardarropasC =new ControllerGuardarropas();
        ControllerEventos eventosC =new ControllerEventos();

        Spark.get("/", sistemaC::landing, new HandlebarsTemplateEngine());
        Spark.get("/registro", sistemaC::registro, new HandlebarsTemplateEngine());
        Spark.get("/perfil/:id", usuarioC::perfil, new HandlebarsTemplateEngine());

        Spark.get("/misGuardarropas", usuarioC::listarGuardarropas, new HandlebarsTemplateEngine());
        Spark.get("/misGuardarropas/:idGuardarropas", guardarropasC::detalleGuardarropa, new HandlebarsTemplateEngine());
        Spark.get("/misGuardarropas/:idGuardarropas/creadorPrendas", sistemaC::creadorPrendas, new HandlebarsTemplateEngine());
        Spark.get("/tipo", guardarropasC::wizardTipoPrenda, new HandlebarsTemplateEngine()); // :idGuardarropas/creadorPrendas/
        Spark.get("/caracteristicas", guardarropasC::wizardCaracteristicas, new HandlebarsTemplateEngine());
        Spark.get("/imagen", guardarropasC::wizardAdjuntarImagen, new HandlebarsTemplateEngine());

        Spark.get("/misEventos", usuarioC::listarEventos, new HandlebarsTemplateEngine());
        Spark.get("/misEventos/:fecha", usuarioC::listarEventosPorFecha, new HandlebarsTemplateEngine());
        Spark.get("/misEventos/:idEvento", eventosC::detalleEvento, new HandlebarsTemplateEngine());
        Spark.get("/misEventos/:idEvento/creadorEventos", sistemaC::creadorEventos, new HandlebarsTemplateEngine());

        Spark.get("/puntuarAtuendos", usuarioC::listarAceptados, new HandlebarsTemplateEngine());
        Spark.get("/puntuarAtuendos/:idAtuendo", usuarioC::puntuadorAtuendos, new HandlebarsTemplateEngine());

        Spark.post("/registro", usuarioC::registrarUsuario, new HandlebarsTemplateEngine());
        Spark.post("/login", usuarioC::loguearUsuario, new HandlebarsTemplateEngine());
        Spark.post("/misGuardarropas/:idGuardarropas/creadorPrendas", guardarropasC::agregarPrenda, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/creadorEventos", eventosC::agregarEvento, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/aceptarSugerencia", eventosC::aceptarSugerencia, new HandlebarsTemplateEngine());
        Spark.post("/misEventos/:idEvento/rechazarSugerencia", eventosC::rechazarSugerencia, new HandlebarsTemplateEngine());
        Spark.post("/puntuarAtuendos/:idAtuendo", usuarioC::puntuarAtuendo, new HandlebarsTemplateEngine());
        
        Spark.get("/favicon.ico", null, new HandlebarsTemplateEngine()); //??

        DebugScreen.enableDebugScreen();
    }
}