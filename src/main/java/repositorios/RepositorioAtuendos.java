package repositorios;

import atuendo.Atuendo;
import db.EntityManagerHelper;

public class RepositorioAtuendos {
    public static Atuendo obtenerAtuendo(String id) {
        int idAtuendo = Integer.parseInt(id);
        return EntityManagerHelper.getEntityManager().find(Atuendo.class, idAtuendo);
    }
}
