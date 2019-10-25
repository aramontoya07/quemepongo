package eventos;
import db.EntityManagerHelper;
import usuario.Usuario;

public class RepoAsistenciaEventos{

    public static AsistenciaEvento obtenerAsistencia(String charIdEvento, String charIdUsuario){
        int idUsuario = Integer.parseInt(charIdUsuario);
        int idEvento = Integer.parseInt(charIdEvento);
        Usuario usuario = EntityManagerHelper.getEntityManager().find(Usuario.class, idUsuario);
        Evento evento = EntityManagerHelper.getEntityManager().find(Evento.class,idEvento);
        return usuario.obtenerAsistencia(evento);
    }

    public static void persisitirAsistencia(AsistenciaEvento asistencia) {
        EntityManagerHelper.beginTransaction();
        asistencia.persistir();
        EntityManagerHelper.commit();
    }
}