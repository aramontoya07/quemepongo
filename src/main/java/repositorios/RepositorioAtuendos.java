package repositorios;

import atuendo.Atuendo;
import db.EntityManagerHelper;

public class RepositorioAtuendos {
    public static Atuendo obtenerAtuendo(String id) {
        int idAtuendo = Integer.parseInt(id);
        Atuendo atuendo = EntityManagerHelper.getEntityManager().find(Atuendo.class, idAtuendo);
        EntityManagerHelper.closeEntityManager();
        return atuendo;
    }
}
