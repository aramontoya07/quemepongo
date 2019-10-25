package usuario;
import db.EntityManagerHelper;

public class RepoGuardarropa{

    public static Guardarropa obtenerGuardarropa(String id){
        int idUsuario = Integer.parseInt(id);
        return EntityManagerHelper.getEntityManager().find(Guardarropa.class, idUsuario);
    }

    public static void persistirGuardarropa(Guardarropa guardarropa) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(guardarropa);
        EntityManagerHelper.commit();
    }
}