package repositorios;
import db.EntityManagerHelper;
import excepciones.RepositorioException;
import usuario.Usuario;

import javax.persistence.TypedQuery;

public class RepositorioUsuarios {

    public static Usuario obtenerUsuario(String id) throws RepositorioException{
        int idUsuario = Integer.parseInt(id);
        Usuario usuario = EntityManagerHelper.getEntityManager().find(Usuario.class, idUsuario);
        if(usuario == null){
            throw new RepositorioException("No se pudo encontrar al usuario con id " + id + " en el respositorio");
        }
        return usuario;
    }

    public static void persistirUsuario(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }

    public static Usuario obtenerUsuarioPorMailYContra(String mail, String contrasenia) throws RepositorioException {
        try{
            TypedQuery <Usuario> query = EntityManagerHelper.getEntityManager()
                    .createQuery("SELECT u FROM Usuario u WHERE mail = '" + mail +"' AND contrasenia = '" + contrasenia + "'"
                            , Usuario.class);
            return query.getSingleResult();
        }catch(Exception e){
            throw new RepositorioException("No se pudo encontrar al usuario con mail " + mail + " y contrasenia " + contrasenia);
        }
    }
}