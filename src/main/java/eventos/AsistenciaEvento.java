package eventos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import atuendo.SugerenciasClima;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import org.uqbar.commons.model.annotations.Observable;
import usuario.Usuario;
@Observable
public class AsistenciaEvento{
	private Evento evento;
	private Set<SugerenciasClima> sugerenciasEvento = new HashSet<>();
	
	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}

    public Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoLejanoException(); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException(); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }

	void generarSugerenciasParaEvento(Usuario usuario) {
		sugerenciasEvento = usuario.pedirSugerenciaSegunClima(evento.getUbicacion());
		usuario.notificarSugerenciasListas(this);
	}
	
	
	boolean ocurreEntre(LocalDateTime fechaMinima, LocalDateTime fechaMaxima){
		return evento.getFecha().isAfter(fechaMinima) && evento.getFecha().isBefore(fechaMaxima);
	}

	public Evento getEvento() {
		return evento;
	}

}
