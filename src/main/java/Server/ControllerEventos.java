package Server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import eventos.AsistenciaEvento;
import eventos.Evento;
import eventos.Frecuencia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class ControllerEventos {
    
    public ModelAndView detalleEvento(Request req, Response res) {
        String idUsuario = req.attribute("idUsuario");
        String idEvento = req.params("idEvento");

        AsistenciaEvento asistencia = (new SetUpUsuario()).setear().getCalendarioEventos().getEventos().stream().filter(a -> a.getEvento().getId() == new Integer(idEvento)).findFirst().orElse(null);

        List<Atuendo> atuendos = new ArrayList<Atuendo>();

        asistencia.getSugerenciasEvento().forEach( sc -> 
            atuendos.addAll(sc.getExactas())
        );

        asistencia.getSugerenciasEvento().forEach(sc -> 
            atuendos.addAll(sc.getAproximadas())
        );

        Map<String, Object> model = new HashMap<String, Object>();
        
        model.put("asistencia", asistencia);
        model.put("atuendos", atuendos);
        model.put("idUsuario", idUsuario);

        return new ModelAndView(model, "detalleEvento.hbs");
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
}