package repositorios;

import db.EntityManagerHelper;
import eventos.AsistenciaEvento;

public class RepositorioAsistenciaEventos{

    public static AsistenciaEvento obtenerAsistencia(String id){
        int idAsistencia = Integer.parseInt(id);
        final AsistenciaEvento asistenciaEvento = EntityManagerHelper.getEntityManager().find(AsistenciaEvento.class, idAsistencia);
        return asistenciaEvento;
    }
}