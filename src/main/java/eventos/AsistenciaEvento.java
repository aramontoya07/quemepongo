package eventos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import atuendo.SugerenciasClima;

import db.EntityManagerHelper;
import excepciones.EventoException;
import db.EntidadPersistente;
import usuario.Usuario;

@Entity
public class AsistenciaEvento extends EntidadPersistente{
	
	@OneToOne
	private Evento evento;
	
	@OneToMany(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "Id_AsistenciaEvento")
	public Set<SugerenciasClima> sugerenciasEvento = new HashSet<>();

	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}

    Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoException("Falta mucho para el evento"); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new EventoException("No hay sugerencias que se adapten al evento"); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }

	public void generarSugerenciasParaEvento(Usuario usuario) {
		sugerenciasEvento = usuario.pedirSugerenciaSegunClima(evento.getUbicacion());
		usuario.notificarSugerenciasListas(this);
	}
	
	public boolean esDeFecha(Integer dia, Integer mes, Integer anio){
		return evento.getFecha().getDayOfMonth() == dia && evento.getFecha().getMonthValue() == mes && evento.getFecha().getYear() == anio;
	}


	public void persistir(){
		evento.persistir();
		sugerenciasEvento.forEach(sugerenciasClima -> sugerenciasClima.persistir());
		EntityManagerHelper.getEntityManager().persist(this);
	}

	boolean ocurreEntre(LocalDateTime fechaMinima, LocalDateTime fechaMaxima){
		return evento.getFecha().isAfter(fechaMinima) && evento.getFecha().isBefore(fechaMaxima);
	}
	
	public boolean noTieneSugerencias() {
		return sugerenciasEvento.isEmpty();
	}

	public Evento getEvento() {
		return evento;
	}

	boolean esCercanaYEsEn(String ubicacion) {
		return evento.esEventoCercanoYOcurreEn(ubicacion);
	}

    public boolean esDeEvento(Evento eventoRecibido) {
		return eventoRecibido.equals(evento);
    }

	public Set<SugerenciasClima> getSugerenciasEvento() {
		return sugerenciasEvento;
	}

	public void setSugerenciasEvento(Set<SugerenciasClima> sugerenciasEvento) {
		this.sugerenciasEvento = sugerenciasEvento;
	}
}
