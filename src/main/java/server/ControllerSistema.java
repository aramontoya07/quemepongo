package server;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerSistema {

    private static final String ID_USUARIO = "idUsuario";

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
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
        String id = req.params("idGuardarropas");
        model.put("id", id);
        return new ModelAndView(model, "creadorPrendas.hbs");
    }

    public ModelAndView creadorEventos(Request req, Response res){
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(idUsuario == null){
            return new ModelAndView(model, "error.hbs");
        }
        return new ModelAndView(null, "creadorEventos.hbs");
    }
}
