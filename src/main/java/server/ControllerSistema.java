package server;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerSistema {
    public ModelAndView landing(Request req, Response res) {
        String mensajeDeError = req.body();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("Error", mensajeDeError);
        return new ModelAndView(model, "landing.hbs");
    }

    public ModelAndView registro(Request req, Response res) {
        String mensajeDeError = req.body();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("Error", mensajeDeError);
        return new ModelAndView(model, "registro.hbs");
    }

    public ModelAndView creadorPrendas(Request req, Response res) {
        String id = req.params("idGuardarropas");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", id);
        return new ModelAndView(model, "creadorPrendas.hbs");
    }

    public ModelAndView creadorEventos(Request req, Response res){
        return new ModelAndView(null, "creadorEventos.hbs");
    }
}
