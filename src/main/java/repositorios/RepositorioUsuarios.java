package repositorios;
import db.EntityManagerHelper;
import usuario.Usuario;

public class RepositorioUsuarios {

    public static Usuario obtenerUsuario(String id){
        int idUsuario = Integer.parseInt(id);
        return EntityManagerHelper.getEntityManager().find(Usuario.class, idUsuario);
    }

    public static void persistirUsuario(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }

    public static Usuario obtenerUsuarioPorMailYContra(String mail, String contrasenia) {
        //TODO
        return obtenerUsuario("1");
    }
}