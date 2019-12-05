package server;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import repositorios.RepositorioUsuarios;
import usuario.Usuario;

public class ActivadorGenerador implements Job {
    public void execute(JobExecutionContext contexto) {
        List<Usuario> usuarios = RepositorioUsuarios.ObtenerUsuariosTotales();
        usuarios.forEach(usuario -> usuario.generarSugerenciasNecesarias());
        System.out.println("ejecute qcyo");
    }
}