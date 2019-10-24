package Server;

import java.util.ArrayList;
import java.util.List;

import atuendo.Atuendo;
import eventos.AsistenciaEvento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class ControllerEventos {
    
    public ModelAndView detalleEvento(Request req, Response res) {
        String idUsuario = req.attribute("idUsuario");
        String idEvento = req.params("idEvento");
        AsistenciaEvento asistenciaEvento = new AsistenciaEvento(null); //@TODO: obtener asistencia a evento inidicado del usuario logueado
        return new ModelAndView(asistenciaEvento, "eventosPorFecha.hbs");
    }

    public ModelAndView agregarEvento(Request req, Response res) {
        String idUsuario = req.attribute("idUsuario");
        
        String titulo = req.params("titulo");
        String fecha = req.params("fecha");
        String ubicacion = req.params("ubicacion");
        String frecuencia = req.params("frecuencia");

        //@TODO: agregar el evento al usuario logueado

        res.redirect("/misEventos/:" + req.params("idEvento"));
        return null;
    }

    public ModelAndView aceptarSugerencia(Request req, Response res) {
        String idUsuario = req.attribute("idUsuario");
        Usuario usuario = null; //@TODO: obtener usuario logueado
        String idAtuendo = req.params("idAtuendo");
        Atuendo atuendo = null; //@TODO: obtener atuendo indicado
        usuario.aceptarAtuendo(atuendo);
        res.redirect("/misEventos/:idEvento");
        return null;
    }

    public ModelAndView rechazarSugerencia(Request req, Response res) {
        String idUsuario = req.attribute("idUsuario");
        Usuario usuario = null; //@TODO: obtener usuario logueado
        String idAtuendo = req.params("idAtuendo");
        Atuendo atuendo = null; //@TODO: obtener atuendo indicado
        usuario.rechazarAtuendo(atuendo);
        res.redirect("/misEventos/:idEvento");
        return null;
    }

}