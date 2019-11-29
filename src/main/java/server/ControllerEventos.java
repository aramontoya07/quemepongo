package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import atuendo.Atuendo;
import atuendo.UsoAtuendo;
import db.EntityManagerHelper;
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
    List<Atuendo> atuendosExactos;
    List<Atuendo> atuendosAproximados;

    public ModelAndView detalleEvento(Request req, Response res) {
        String idAsistencia = req.params(ID_ASISTENCIA);
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        AsistenciaEvento asistencia = RepositorioAsistenciaEventos.obtenerAsistencia(idAsistencia);

        atuendosExactos = new ArrayList<Atuendo>();
        atuendosAproximados = new ArrayList<Atuendo>();

        asistencia.getSugerenciasEvento().forEach( sc -> 
            atuendosExactos.addAll(sc.getExactas())
        );
        asistencia.getSugerenciasEvento().forEach(sc -> 
            atuendosAproximados.addAll(sc.getAproximadas())
        );

        Set<UsoAtuendo> usosAceptados = usuario.getAceptados();
        Set<UsoAtuendo> usosRechazados = usuario.getUsosRechazados();
        List<Integer> idsAceptadas = usosAceptados.stream().map(uso -> uso.getAtuendo().getId()).collect(Collectors.toList());
        List<Integer> idsRechazadas = usosRechazados.stream().map(uso -> uso.getAtuendo().getId()).collect(Collectors.toList());

        List<Integer> ids = new ArrayList<Integer>();
        ids.addAll(idsAceptadas);
        ids.addAll(idsRechazadas);

        atuendosAproximados = atuendosAproximados.stream().filter(atuendo -> atuendo.noEsDeId(ids))
                .collect(Collectors.toList());
        atuendosExactos = atuendosExactos.stream().filter(atuendo -> atuendo.noEsDeId(ids))
                .collect(Collectors.toList());
    
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("asistencia", asistencia);
        model.put("atuendosExactos", atuendosExactos);
        model.put("cantidadExactos", atuendosExactos.size());
        model.put("atuendosAproximados", atuendosAproximados);
        model.put("cantidadAproximadas", atuendosAproximados.size());
        return new ModelAndView(model, "detalleEvento.hbs");
    }

    public LocalDateTime formatearFecha(String fecha){
        int mes = Integer.parseInt(fecha.substring(0,2));
        int dia = Integer.parseInt(fecha.substring(3,5));
        int anio = Integer.parseInt(fecha.substring(6,10));
        return LocalDateTime.of(anio,mes,dia,0,0);
    }

    public ModelAndView agregarEvento(Request req, Response res) {
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        String titulo = req.queryParams("titulo");
        LocalDateTime fecha = formatearFecha(req.queryParams("fecha"));
        String ubicacion = req.queryParams("ubicacion");

        Evento evento = new Evento(titulo, fecha, ubicacion, Frecuencia.UNICO);
        
        EntityManagerHelper.beginTransaction();
        usuario.asistirAEvento(evento);
        EntityManagerHelper.commit();

        res.redirect("/misEventos");
        return null;
    }
    
    public ModelAndView generarSugerencias(Request req, Response res){
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        String idAsistencia = req.params(ID_ASISTENCIA);
        AsistenciaEvento asistencia = RepositorioAsistenciaEventos.obtenerAsistencia(idAsistencia);
        EntityManagerHelper.beginTransaction();
        asistencia.generarSugerenciasParaEvento(usuario);
        EntityManagerHelper.commit();
        res.redirect("/misEventos/" + idAsistencia);
        return null;
    }
}