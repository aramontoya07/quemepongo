package Server;

import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
    public static void main(String[] args) {
        //RepositorioGuardarropas.instance().findByUsuario(new Usuario());

        Spark.port(9000);
        Spark.init();

        ControllerUsuario controller =
                new ControllerUsuario();

        Spark.get("/hola", controller::obtenerUsuarios, new HandlebarsTemplateEngine());
        
        Spark.get("/favicon.ico", null, new HandlebarsTemplateEngine());
        
        DebugScreen.enableDebugScreen();
    }

}