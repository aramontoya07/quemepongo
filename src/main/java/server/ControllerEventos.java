package server;

import atuendo.Atuendo;
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

import java.time.LocalDateTime;
import java.util.*;

public class ControllerEventos {
    private static final String ID_USUARIO = "idUsuario";
    private static final String ID_ASISTENCIA = "idEvento";
    List<Atuendo> atuendosExactos;
    List<Atuendo> atuendosAproximados;
    private Server server;

    public ControllerEventos() {
        this.server = Server.instancia();
    }

    public ModelAndView detalleEvento(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        /*try{*/
            String idAsistencia = req.params(ID_ASISTENCIA);
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error.hbs");
        }
            Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
            AsistenciaEvento asistencia = RepositorioAsistenciaEventos.obtenerAsistencia(idAsistencia);

            atuendosExactos = new ArrayList<Atuendo>();
            atuendosAproximados = new ArrayList<Atuendo>();

            if(asistencia.getSugerenciasEvento() == null){
                asistencia.sugerenciasEvento = new HashSet<>();
            }

            asistencia.getSugerenciasEvento().forEach( sugerenciasClima ->
                    atuendosExactos.addAll(sugerenciasClima.getExactas())
            );
            asistencia.getSugerenciasEvento().forEach(sugerenciasClima ->
                    atuendosAproximados.addAll(sugerenciasClima.getAproximadas())
            );

            /*Set<UsoAtuendo> usosAceptados = usuario.getAceptados();
            Set<UsoAtuendo> usosRechazados = usuario.getUsosRechazados();
            List<Integer> idsAceptadas = usosAceptados.stream().map(uso -> uso.getAtuendo().getId()).collect(Collectors.toList());
            List<Integer> idsRechazadas = usosRechazados.stream().map(uso -> uso.getAtuendo().getId()).collect(Collectors.toList());

            List<Integer> ids = new ArrayList<Integer>();
            ids.addAll(idsAceptadas);
            ids.addAll(idsRechazadas);

            atuendosAproximados = atuendosAproximados.stream().filter(atuendo -> atuendo.noEsDeId(ids))
                    .collect(Collectors.toList());
            atuendosExactos = atuendosExactos.stream().filter(atuendo -> atuendo.noEsDeId(ids))
                    .collect(Collectors.toList());*/


            model.put("asistencia", asistencia);
            model.put("sugerenciasEvento", asistencia.getSugerenciasEvento());
            model.put("atuendosExactos", atuendosExactos);
            model.put("cantidadExactos", atuendosExactos.size());
            model.put("atuendosAproximados", atuendosAproximados);
            model.put("cantidadAproximadas", atuendosAproximados.size());
            return new ModelAndView(model, "detalleEvento.hbs");
        /*} catch(Exception e){
            e.printStackTrace();
            return new ModelAndView(model, "detalleEvento.hbs");
        }*/


    }

    public LocalDateTime formatearFecha(String fecha){
        int mes = Integer.parseInt(fecha.substring(0,2));
        int dia = Integer.parseInt(fecha.substring(3,5));
        int anio = Integer.parseInt(fecha.substring(6,10));
        return LocalDateTime.of(anio,mes,dia,0,0);
    }

    public ModelAndView agregarEvento(Request req, Response res) {
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error.hbs");
        }
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        String titulo = req.queryParams("titulo");
        LocalDateTime fecha = formatearFecha(req.queryParams("fecha"));
        String ubicacion = req.queryParams("ubicacion");

        Evento evento = new Evento(titulo, fecha, ubicacion, Frecuencia.UNICO);
        
        EntityManagerHelper.beginTransaction();
        usuario.asistirAEvento(evento);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();

        res.redirect("/misEventos");
        return null;
    }
    
    public ModelAndView generarSugerencias(Request req, Response res){
        Map<String, Object> model = new HashMap<String, Object>();
        String idUsuario = req.session().attribute(ID_USUARIO);
        if(!server.getTokens().contains(req.cookie("token"))) {
            return new ModelAndView(model, "error.hbs");
        }
        Usuario usuario = RepositorioUsuarios.obtenerUsuario(idUsuario);
        String idAsistencia = req.params(ID_ASISTENCIA);
        AsistenciaEvento asistencia = RepositorioAsistenciaEventos.obtenerAsistencia(idAsistencia);
        EntityManagerHelper.beginTransaction();
        asistencia.generarSugerenciasParaEvento(usuario);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        res.redirect("/misEventos/" + idAsistencia);
        return null;
    }
}