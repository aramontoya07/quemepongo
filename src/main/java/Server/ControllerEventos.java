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

    public ModelAndView detalleEvento(Request req, Response res) {
        String idAsistencia = req.params(ID_ASISTENCIA);
        String idUsuario = req.session().attribute(ID_USUARIO);
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        AsistenciaEvento asistencia = RepositorioAsistenciaEventos.obtenerAsistencia(idAsistencia);
        List<Atuendo> atuendos = new ArrayList<Atuendo>();
        asistencia.getSugerenciasEvento().forEach( sc -> 
            atuendos.addAll(sc.getExactas())
        );
        asistencia.getSugerenciasEvento().forEach(sc -> 
            atuendos.addAll(sc.getAproximadas())
        );

        Set<UsoAtuendo> usos = usuario.getAceptados();
        Set<UsoAtuendo> usos2 = usuario.getUsosRechazados();
        usos.addAll(usos2);
        List<Integer> ids = usos.stream().map(uso -> uso.getAtuendo().getId()).collect(Collectors.toList());

        List<Atuendo> atuendosFiltrados = atuendos.stream().filter(atuendo -> atuendo.noEsDeId(ids))
                .collect(Collectors.toList());
        
        System.out.println("me quedo con: ");
        atuendosFiltrados.forEach(atuendo -> System.out.println(atuendo.getId()));
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("asistencia", asistencia);
        model.put("atuendos", atuendosFiltrados);
        return new ModelAndView(model, "detalleEvento.hbs");
    }

    public LocalDateTime formatearFecha(String fecha){
        System.out.println(fecha);
        int mes = Integer.parseInt(fecha.substring(0,2));
        int dia = Integer.parseInt(fecha.substring(3,5));
        int anio = Integer.parseInt(fecha.substring(6,10));
        System.out.println(dia);
        System.out.println(mes);
        System.out.println(anio);
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
}