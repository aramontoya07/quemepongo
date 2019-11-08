package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import alertas.RepoUsuarios;
import atuendo.Atuendo;
import eventos.AsistenciaEvento;
import eventos.Evento;
import eventos.Frecuencia;
import repositorios.RepositorioAsistenciaEventos;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class ControllerEventos {
    private static final String ID_USUARIO = "idUsuario";
    private static final String ID_ASISTENCIA = "idEvento";

    public ModelAndView detalleEvento(Request req, Response res) {
        String idAsistencia = req.params(ID_ASISTENCIA);
        
        AsistenciaEvento asistencia = RepositorioAsistenciaEventos.obtenerAsistencia(idAsistencia);

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
        return new ModelAndView(model, "detalleEvento.hbs");
    }

    public ModelAndView agregarEvento(Request req, Response res) {
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        String titulo = req.queryParams("titulo");
        //LocalDateTime fecha = req.queryParams("fecha"); // formato: 30/12/2019
        String ubicacion = req.queryParams("ubicacion");
        String frecuencia = req.queryParams("frecuencia");

        Evento evento = new Evento(titulo, null, ubicacion, Frecuencia.UNICO);

        res.redirect("/misEventos/:" + req.params("idEvento"));
        return null;
    }
}