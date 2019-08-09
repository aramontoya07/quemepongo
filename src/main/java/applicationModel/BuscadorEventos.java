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
    private boolean tieneSugerencia;
    private LocalDateTime fecha;

    private Evento evento;
    private Set<SugerenciasClima> sugerenciasEvento = new HashSet<>();

    Usuario usuario;

    public BuscadorEventos(Usuario usuario) {
        this.usuario = usuario;
    }

    
    //ACCIONES
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

    //ACCESORS

    public List<AsistenciaEvento> getResultados() {
        return resultados;
    }
    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaInicio) {
        this.fechaDesde = fechaInicio;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaFin) {
        this.fechaHasta = fechaFin;
    }

    public void setResultados(List<AsistenciaEvento> resultados) {
        this.resultados = resultados;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setTieneSugerencia(boolean tieneSugerencia) {
        this.tieneSugerencia = tieneSugerencia;
    }

    public boolean getTieneSugerencia() {
        return sugerenciasEvento.size() > 0;
    }
}
