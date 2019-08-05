package applicationModel;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import atuendo.SugerenciasClima;
import eventos.AsistenciaEvento;
import eventos.Evento;
import org.uqbar.commons.applicationContext.ApplicationContext; //Es por si usabamos un repositorio???
import org.uqbar.commons.model.annotations.Observable;
import usuario.Usuario;


@Observable
public class BuscadorEventos {
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private List<AsistenciaEvento> resultados;
    private String nombre;
    private String lugar;
    private boolean tieneSugerencia; //Nuestros eventos no tienen esta propiedad. Asi que esto debe ser ILEGAL ¯\_(ツ)_/¯
    private LocalDateTime fecha;

    private Evento evento;
    private Set<SugerenciasClima> sugerenciasEvento = new HashSet<>();

    Usuario usuario;

    //Acciones


    public void search() {
        this.resultados = getEventosEntre(fechaDesde, fechaHasta);
    }

    public List<AsistenciaEvento> getEventosEntre(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
       Set<AsistenciaEvento> setEventos = usuario.getCalendarioEventos().obtenerEventosEntre(fechaDesde, fechaHasta);
       List<AsistenciaEvento> resultados = new ArrayList<>(setEventos);
       return resultados; //Tenemos un set y aparentemente pra usar una tabla necesito una List
    }

    public void clear() {
        this.fechaDesde = null;
        this.fechaHasta = null;
        this.resultados = new ArrayList<>();
    }

    //Accesors. Creo que los setters los uso en caso de crear un evento nuevo. Pero nosotros solo tenemos que listarlos.

    public LocalDateTime getFechaInicio() {
        return fechaDesde;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaHasta = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaHasta;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaHasta = fechaFin;
    }

    public List<AsistenciaEvento> getResultados() {
        return resultados;
    }

    public void setResultados(List<AsistenciaEvento> resultados) {
        this.resultados = resultados;
    }

    public String getNombre() {
        return evento.getNombre();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return evento.getUbicacion();
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean getTieneSugerencia() {
        return sugerenciasEvento.size() > 0;
    }

    public void setTieneSugerencia(boolean tieneSugerencia) {
        this.tieneSugerencia = tieneSugerencia;
    }

    public LocalDateTime getFecha() {
        return evento.getFecha();
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }



}
