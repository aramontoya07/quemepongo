package eventos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import atuendo.SugerenciasClima;

import excepciones.EventoException;
import usuario.Usuario;

import org.uqbar.commons.model.Entity;

public class AsistenciaEvento extends Entity {
	private Evento evento;
	private Set<SugerenciasClima> sugerenciasEvento = new HashSet<SugerenciasClima>();
	
	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}

	public AsistenciaEvento() {

	}

    public Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoException("Falta mucho para el evento"); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new EventoException("No hay sugerencias que se adapten al evento"); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }

	public void generarSugerenciasParaEvento(Usuario usuario) {
		sugerenciasEvento = usuario.pedirSugerenciaSegunClima(evento.getUbicacion());
		usuario.notificarSugerenciasListas(this);
	}
	
	
	public boolean ocurreEntre(LocalDateTime fechaMinima, LocalDateTime fechaMaxima){
		return evento.getFecha().isAfter(fechaMinima) && evento.getFecha().isBefore(fechaMaxima);
	}

	public Evento getEvento() {
		return evento;
	}

}
