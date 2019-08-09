package Repositorio;
import java.util.List;
import java.util.stream.Collectors;

import eventos.AsistenciaEvento;
import eventos.Evento;
import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.exceptions.UserException;

@Observable
public class RepoEventos extends CollectionBasedRepo<Evento> {

    private static RepoEventos instance = new RepoEventos();

    public static RepoEventos repoEventos() {
        return instance;
    }

    public RepoEventos() {
        this.init();
    }

    public void init(){

    }

    @Override
    public Class<Evento> getEntityType() {
        return Evento.class;
    }

    @Override
    public Evento createExample() {
        return new Evento();
    }

    @Override
    protected Predicate<Evento> getCriterio(Evento example) {
        return null;
    }

}
