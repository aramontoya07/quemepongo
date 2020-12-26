package server;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerSistema {
    private Server server;

    private static final String ID_USUARIO = "idUsuario";

    public ControllerSistema() {
        this.server = Server.instancia();
    }

    public ModelAndView landing(Request req, Response res) {
        String mensajeDeError = req.body();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("Error", mensajeDeError);
        return new ModelAndView(model, "landing.hbs");
    }

    public ModelAndView error404(Request req, Response res) {
        String mensajeDeError = req.body();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("Error", mensajeDeError);
        return new ModelAndView(model, "error404.hbs");
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
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
        String id = req.params("idGuardarropas");
        model.put("id", id);
        return new ModelAndView(model, "creadorPrendas.hbs");
    }

    public ModelAndView creadorEventos(Request req, Response res){
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error403.hbs");
        }
        return new ModelAndView(null, "creadorEventos.hbs");
    }
}
