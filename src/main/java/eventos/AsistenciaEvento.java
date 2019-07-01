package eventos;

import java.util.HashSet;
import java.util.Set;

import atuendo.SugerenciasClima;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import usuario.Usuario;

public class AsistenciaEvento{
	private Evento evento;
	private Set<SugerenciasClima> sugerenciasEvento = new HashSet<SugerenciasClima>();
	
	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}

    public Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoLejanoException(); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException(); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }

	public void generarSugerenciasParaEvento(Usuario usuario) {
		sugerenciasEvento = usuario.pedirSugerenciaParaEvento(evento);
	}
}
