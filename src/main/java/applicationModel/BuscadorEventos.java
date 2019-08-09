package applicationModel;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import atuendo.SugerenciasClima;
import eventos.AsistenciaEvento;
import eventos.Evento;
import org.uqbar.commons.model.annotations.Observable;
import usuario.Usuario;


@Observable
public class BuscadorEventos {
    private Date fechaDesde; // Si queremos que sea LocalDateTime tenemos que convertirlo
    private Date fechaHasta;
    private List<AsistenciaEvento> resultados;
    private String nombre;
    private String lugar;
    private boolean tieneSugerencia; //Nuestros eventos no tienen esta propiedad. Asi que esto debe ser ILEGAL ¯\_(ツ)_/¯
    private LocalDateTime fecha;

    private Evento evento;
    private Set<SugerenciasClima> sugerenciasEvento = new HashSet<>();

    Usuario usuario = new Usuario(); //Si no tiene ningun usuario ROMPE! FIXME
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    //ACCIONES
    public LocalDateTime convertirALocalDateTime (Date fecha) {
    	LocalDateTime miFecha = LocalDateTime.ofInstant(fecha.toInstant(), ZoneId.systemDefault());
    	return miFecha;
    }
    
    public void search() {
        this.resultados = getEventosEntre(fechaDesde, fechaHasta);
    }

    public List<AsistenciaEvento> getEventosEntre(Date fechaDesde, Date fechaHasta) {
    	LocalDateTime fechaInicio = convertirALocalDateTime(fechaDesde);
    	LocalDateTime fechaFin = convertirALocalDateTime(fechaHasta);
    	Set<AsistenciaEvento> setEventos = usuario.getCalendarioEventos().obtenerEventosEntre(fechaInicio, fechaFin);
    	List<AsistenciaEvento> resultados = new ArrayList<>(setEventos);
    	return resultados;
    }

    public void clear() {
        this.fechaDesde = null;
        this.fechaHasta = null;
        this.resultados = new ArrayList<>();
    }

    //ACCESORS
    
    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaInicio) {
        this.fechaDesde = fechaInicio;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaFin) {
        this.fechaHasta = fechaFin;
    }

    public List<AsistenciaEvento> getResultados() {
        return resultados;
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

    public boolean getTieneSugerencia() {
        return tieneSugerencia;
    }

    public void setTieneSugerencia(boolean tieneSugerencia) {
        this.tieneSugerencia = tieneSugerencia;
    }
}