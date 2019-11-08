package server;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerSistema {
    public ModelAndView landing(Request req, Response res) {
        return new ModelAndView(null, "landing.hbs");
    }

    public ModelAndView registro(Request req, Response res) {
        return new ModelAndView(null, "registro.hbs");
    }

    public ModelAndView creadorPrendas(Request req, Response res) {
        return new ModelAndView(null, "creadorPrendas.hbs");
    }

    public ModelAndView creadorEventos(Request req, Response res) {
        return new ModelAndView(null, "creadorEventos.hbs");
    }
}
