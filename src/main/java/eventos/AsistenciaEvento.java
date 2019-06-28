package eventos;

import java.util.HashSet;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import atuendo.SugerenciasClima;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import usuario.Usuario;

public class AsistenciaEvento implements Job{
	private Evento evento;
	private Usuario user;
	private Set<SugerenciasClima> sugerenciasEvento = new HashSet<SugerenciasClima>();
	
	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}
	
	public void execute(JobExecutionContext contexto) throws JobExecutionException{
		sugerenciasEvento = user.pedirSugerenciaSegunClima(evento.getUbicacion());
	}

    public Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoLejanoException(); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException(); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }
}
