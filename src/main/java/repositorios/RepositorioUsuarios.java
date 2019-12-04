package repositorios;

import db.EntityManagerHelper;
import excepciones.RepositorioException;
import usuario.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositorioUsuarios {

    public static Usuario obtenerUsuario(String id) throws RepositorioException{
        TypedQuery<Usuario> query = EntityManagerHelper.getEntityManager().createQuery(
                "SELECT u FROM Usuario u WHERE id = " + id, Usuario.class);
        String excepcion = "No se pudo encontrar al usuario con id " + id + " en el respositorio";
        Usuario usuario = obtenerUsuarioConQuery(query, excepcion);
        EntityManagerHelper.closeEntityManager();
        return usuario;
    }

    public static void persistirUsuario(Usuario usuario) throws RepositorioException{
        try{
            obtenerUsuarioPorMail(usuario.getMail());
        }catch(RepositorioException e){ //si no encontre el usuario con mail en la base de datos, puedo crear al nuevo usuario con este mail
            EntityManagerHelper.beginTransaction();
            EntityManagerHelper.getEntityManager().persist(usuario);
            EntityManagerHelper.commit();
            EntityManagerHelper.closeEntityManager();
            return;
        }
            throw new RepositorioException("El mail que se intento ingresar pertenece a un usuario ya creado");
    }

    private static Usuario obtenerUsuarioPorMail(String mail) throws RepositorioException {
            TypedQuery<Usuario> query = EntityManagerHelper.getEntityManager().createQuery(
                    "SELECT u FROM Usuario u WHERE mail = '" + mail + "'", Usuario.class);
            String excepcion = "No se pudo encontrar al usuario con mail " + mail;
            Usuario usuario = obtenerUsuarioConQuery(query, excepcion);
            EntityManagerHelper.closeEntityManager();
            return usuario;
    }

    public static Usuario obtenerUsuarioPorMailYContra(String mail, String contra) throws RepositorioException {
        String contrasenia = new Usuario().convertirSHA256(contra);
        TypedQuery<Usuario> query = EntityManagerHelper.getEntityManager().createQuery(
                "SELECT u FROM Usuario u WHERE mail = '" + mail + "' AND contrasenia = '" + contrasenia + "'",
                Usuario.class);
        String excepcion = "No se pudo encontrar al usuario con mail " + mail + " y contrasenia " + contra;
        Usuario usuario = obtenerUsuarioConQuery(query, excepcion);
        EntityManagerHelper.closeEntityManager();
        return usuario;
    }

    private static Usuario obtenerUsuarioConQuery(TypedQuery<Usuario> query, String excepcion) throws RepositorioException{
        try{
            Usuario usuario = query.getSingleResult();
            return usuario;
        }catch(NoResultException e){
            throw new RepositorioException(excepcion);
        }
    }
}