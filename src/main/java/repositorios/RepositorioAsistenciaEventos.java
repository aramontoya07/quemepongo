package repositorios;
import org.hibernate.metamodel.binding.AssociationAttributeBinding;

import db.EntityManagerHelper;
import eventos.AsistenciaEvento;

public class RepositorioAsistenciaEventos{

    public static AsistenciaEvento obtenerAsistencia(String id){
        int idAsistencia = Integer.parseInt(id);
        return EntityManagerHelper.getEntityManager().find(AsistenciaEvento.class, idAsistencia);
    }
}