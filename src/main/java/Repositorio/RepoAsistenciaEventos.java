package Repositorio;
import java.util.List;
import java.util.stream.Collectors;

import eventos.AsistenciaEvento;
import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.exceptions.UserException;

@Observable
public class RepoAsistenciaEventos extends CollectionBasedRepo<AsistenciaEvento> {

    private static RepoAsistenciaEventos instance = new RepoAsistenciaEventos();

    public static RepoAsistenciaEventos repositorioAsistenciaEventos() {
        return instance;
    }

    public RepoAsistenciaEventos() {
        this.init();
    }

    public void init(){

    }

    @Override
    public Class<AsistenciaEvento> getEntityType() {
        return AsistenciaEvento.class;
    }

    @Override
    public AsistenciaEvento createExample() {
        return new AsistenciaEvento();
    }

    @Override
    protected Predicate<AsistenciaEvento> getCriterio(AsistenciaEvento example) {
        return null;
    }
}
