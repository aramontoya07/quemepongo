package repositorios;

import db.EntityManagerHelper;
import eventos.AsistenciaEvento;

public class RepositorioAsistenciaEventos{

    public static AsistenciaEvento obtenerAsistencia(String id){
        int idAsistencia = Integer.parseInt(id);
        return EntityManagerHelper.getEntityManager().find(AsistenciaEvento.class, idAsistencia);
    }
}