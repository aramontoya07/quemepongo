package Server;

import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
    public static void main(String[] args) {
        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());

        Spark.port(9000);
        Spark.init();

        ControllerUsuario usuarioC =new ControllerUsuario();
        ControllerSistema sistemaC = new ControllerSistema();

        Spark.get("/", sistemaC::landing, new HandlebarsTemplateEngine());
        Spark.get("/registro", sistemaC::registro, new HandlebarsTemplateEngine());
        Spark.get("/perfil/:id", usuarioC::perfil, new HandlebarsTemplateEngine());
        Spark.get("/guardarropas", usuarioC::listarGuardarropas, new HandlebarsTemplateEngine());
    
        Spark.post("/registro", usuarioC::registrarUsuario, new HandlebarsTemplateEngine());
        Spark.post("/login", usuarioC::loguearUsuario, new HandlebarsTemplateEngine());
        
        Spark.get("/favicon.ico", null, new HandlebarsTemplateEngine());
        
        DebugScreen.enableDebugScreen();
    }

}