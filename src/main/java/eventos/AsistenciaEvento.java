package eventos;

import java.util.HashSet;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

import atuendo.SugerenciasClima;
import excepciones.EventoLejanoException;
import excepciones.NingunaSugerenciaParaEventoException;
import usuario.Usuario;

public class AsistenciaEvento implements Job{
	private Evento evento;
	private Set<SugerenciasClima> sugerenciasEvento = new HashSet<SugerenciasClima>();
	
	public AsistenciaEvento(Evento eventoAsignado) {
		evento = eventoAsignado;
	}
	
	public void execute(JobExecutionContext contexto) throws JobExecutionException{
		try {
			SchedulerContext context = contexto.getScheduler().getContext();
			Usuario usuario = (Usuario) context.get("usuario");
			Evento evento = (Evento) context.get("evento");
			sugerenciasEvento = usuario.pedirSugerenciaSegunClima(evento.getUbicacion());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

    public Set<SugerenciasClima> pedirSugerencias(){
        if(evento.esEventoLejano()) throw new EventoLejanoException(); //Lo pidio muy antes
        if(sugerenciasEvento.isEmpty()) throw new NingunaSugerenciaParaEventoException(); //No hay sugerencias que se adapten
        return sugerenciasEvento;
    }
}
