package eventos;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

import usuario.Usuario;

public class ActivadorEvento  implements Job{
	
	public void execute(JobExecutionContext contexto) throws JobExecutionException{
		try {
			SchedulerContext context = contexto.getScheduler().getContext();
			Usuario usuario = (Usuario) context.get("usuario");
			AsistenciaEvento asistencia = (AsistenciaEvento) context.get("AsistenciaEvento");
			asistencia.generarSugerenciasParaEvento(usuario);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
