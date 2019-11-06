package repositorios;
import db.EntityManagerHelper;
import eventos.AsistenciaEvento;
import eventos.Evento;
import usuario.Usuario;

public class RepositorioAsistenciaEventos{

    public static AsistenciaEvento obtenerAsistencia(String charIdEvento, String charIdUsuario){
        int idUsuario = Integer.parseInt(charIdUsuario);
        int idEvento = Integer.parseInt(charIdEvento);
        Usuario usuario = EntityManagerHelper.getEntityManager().find(Usuario.class, idUsuario);
        Evento evento = EntityManagerHelper.getEntityManager().find(Evento.class,idEvento);
        return usuario.obtenerAsistencia(evento);
    }
}