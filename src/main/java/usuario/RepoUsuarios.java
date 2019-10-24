package usuario;
import db.EntityManagerHelper;

public class RepoUsuarios{

    public static Usuario obtenerUsuario(String id){
        int idUsuario = Integer.parseInt(id);
        return EntityManagerHelper.getEntityManager().find(Usuario.class, idUsuario);
    }

    public static void persistirUsuario(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }
}