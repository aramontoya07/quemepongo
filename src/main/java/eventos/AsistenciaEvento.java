package eventos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import atuendo.SugerenciasClima;

import excepciones.EventoException;
import usuario.Usuario;


public class AsistenciaEvento {
	private Evento evento;
	private Set<SugerenciasClima> sugerenciasEvento = new HashSet<>();

	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}

    Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoException("Falta mucho para el evento"); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new EventoException("No hay sugerencias que se adapten al evento"); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }

	void generarSugerenciasParaEvento(Usuario usuario) {
		sugerenciasEvento = usuario.pedirSugerenciaSegunClima(evento.getUbicacion());
		usuario.notificarSugerenciasListas(this);
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
}
